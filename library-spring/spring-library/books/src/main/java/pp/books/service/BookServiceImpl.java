package pp.books.service;


import org.springframework.stereotype.Service;
import pp.books.domain.Book;
import pp.books.domain.Opinion;
import pp.books.dto.BookUpdateStatusDto;
import pp.books.dto.OpinionDto;
import pp.books.dto.ReservationUpdateStatusDto;
import pp.books.enums.BookKind;
import pp.books.enums.BookStatus;
import pp.books.exception.BookIdentifierAlreadyExists;
import pp.books.kafka.ReservationsProducer;
import pp.books.repository.BookRepository;
import pp.books.repository.OpinionRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String ADMIN_USERNAME = "admin";

    private final ReservationsProducer reservationsProducer;

    private final BookRepository bookRepository;

    private final OpinionRepository opinionRepository;

    public BookServiceImpl(ReservationsProducer reservationsProducer, BookRepository bookRepository, OpinionRepository opinionRepository) {
        this.reservationsProducer = reservationsProducer;
        this.bookRepository = bookRepository;
        this.opinionRepository = opinionRepository;
    }

    @Override
    public void addBook(Book book) {
        if (bookRepository.findByIdentifier(book.getIdentifier()).isEmpty()) {
            bookRepository.save(book);
        } else {
            throw new BookIdentifierAlreadyExists("Book identifier already exists");
        }

    }

    @Override
    public List<Book> getClassicLibraryBooks() {
        return (List<Book>) bookRepository.findByOwnerUsername(ADMIN_USERNAME);
    }

    @Override
    public List<Book> getUserRentalServiceBooks() {
        return (List<Book>) bookRepository.findByOwnerUsernameNotLike(ADMIN_USERNAME);
    }

    @Override
    public void deleteBookById(long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent() && BookStatus.AVAILABLE.equals(optionalBook.get().getBookStatus())) {
            opinionRepository.findByBookId(bookId).forEach(opinionRepository::delete);
            bookRepository.deleteById(bookId);
        }
    }

    @Override
    public Book getBookById(long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        return book.orElse(null);
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void addOpinion(OpinionDto opinionDto) {
        Opinion opinion = convertToOpinion(opinionDto);
        Book book = opinionDto.getBook();
        double avgRate = book.getAvgRate();
        if (book.getOpinions() != null) {
            double newAvgRate = (avgRate * book.getOpinions().size() + opinion.getGrade()) / (book.getOpinions().size() + 1);
            book.setAvgRate(newAvgRate);
        } else {
            book.setAvgRate(opinion.getGrade());
        }
        bookRepository.save(book);
        opinionRepository.save(opinion);
    }

    @Override
    public List<Book> getBooksOrderedByAvgRate() {
        return ((List<Book>) bookRepository.findAllByOrderByAvgRateDesc()).stream()
                .filter(book -> book.getAvgRate() != 0).collect(Collectors.toList());
    }

    @Override
    public void updateBookStatus(BookUpdateStatusDto bookUpdateStatusDto) {
        Optional<Book> book = bookRepository.findByIdentifier(bookUpdateStatusDto.getBookIdentifier());
        if (book.isPresent()) {
            book.get().setBookStatus(BookStatus.valueOf(bookUpdateStatusDto.getNewBookStatus()));
            bookRepository.save(book.get());
        }

        if (BookStatus.AVAILABLE.toString().equals(bookUpdateStatusDto.getNewBookStatus())) {
            reservationsProducer.sendMessage(
                    new ReservationUpdateStatusDto("FINISHED", bookUpdateStatusDto.getReservationId()));
        } else {
            reservationsProducer.sendMessage(
                    new ReservationUpdateStatusDto("ACTIVE", bookUpdateStatusDto.getReservationId()));
        }
    }

    @Override
    public String savePerformanceTest() {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 3000; i++) {
            UUID uuid = UUID.randomUUID();
            books.add(
                    Book.builder()
                            .title("title" + i)
                            .author("author" + i)
                            .identifier(uuid.toString().substring(0, 10))
                            .bookKind(BookKind.ACTION)
                            .bookStatus(BookStatus.AVAILABLE)
                            .releaseDate(new Date())
                            .ownerUsername("admin")
                            .avgRate(5.0)
                            .opinions(Collections.emptySet())
                            .build()
            );
        }

        long startTime = System.currentTimeMillis();
        for (Book book : books) {
            bookRepository.save(book);
        }
        long duration = System.currentTimeMillis() - startTime;


        return "saving finished - " + duration + " ms";
    }

    @Override
    public String readPerformanceTest() {
        long startTime = System.currentTimeMillis();
        Iterable<Book> all = bookRepository.findAll();
        long duration = System.currentTimeMillis() - startTime;

        return "reading books finished - " + duration + " ms, size: " + ((Collection<?>) all).size();
    }

    private Opinion convertToOpinion(OpinionDto opinionDto) {
        Opinion opinion = new Opinion();
        opinion.setId(opinion.getId());
        opinion.setGrade(opinionDto.getGrade());
        opinion.setReview(opinionDto.getReview());
        opinion.setBook(opinionDto.getBook());
        return opinion;
    }
}
