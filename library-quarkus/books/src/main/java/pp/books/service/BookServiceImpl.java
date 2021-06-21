package pp.books.service;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import pp.books.domain.Book;
import pp.books.domain.Opinion;
import pp.books.dto.BookUpdateStatusDto;
import pp.books.dto.OpinionDto;
import pp.books.dto.ReservationUpdateStatusDto;
import pp.books.enums.BookKind;
import pp.books.enums.BookStatus;
import pp.books.kafka.ReservationsProducer;
import pp.books.repository.BookRepository;
import pp.books.repository.OpinionRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
@ActivateRequestContext
public class BookServiceImpl implements BookService {

    private static final String ADMIN_USERNAME = "admin";
    public static final String RESERVATION_STATUS_FINISHED = "FINISHED";
    public static final String RESERVATION_STATUS_ACTIVE = "ACTIVE";

    private final ReservationsProducer reservationsProducer;

    private final BookRepository bookRepository;

    private final OpinionRepository opinionRepository;

    public BookServiceImpl(ReservationsProducer reservationsProducer, BookRepository bookRepository, OpinionRepository opinionRepository) {
        this.reservationsProducer = reservationsProducer;
        this.bookRepository = bookRepository;
        this.opinionRepository = opinionRepository;
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        if (!bookRepository.findByIdentifier(book.getIdentifier()).isPresent()) {
            bookRepository.save(book);
        } else {
            throw new WebApplicationException(Response.status(409).entity("Book identifier already exists").build());
        }

    }

    @Override
    public List<Book> getClassicLibraryBooks() {
        return bookRepository.findByOwnerUsername(ADMIN_USERNAME);
    }

    @Override
    public List<Book> getUserRentalServiceBooks() {
        return bookRepository.findByOwnerUsernameNotLike(ADMIN_USERNAME);
    }

    @Override
    public void deleteBookById(long bookId) {
        Optional<Book> optionalBook = bookRepository.findByIdOptional(bookId);
        if (optionalBook.isPresent() && BookStatus.AVAILABLE.equals(optionalBook.get().getBookStatus())) {
            opinionRepository.findByBookId(bookId).forEach(opinionRepository::delete);
            bookRepository.deleteById(bookId);
        }
    }

    @Override
    public Book getBookById(long bookId) {
        Optional<Book> book = bookRepository.findByIdOptional(bookId);
        return book.orElse(null);
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    @Transactional
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
        bookRepository.update(book);
        opinionRepository.save(opinion);
    }

    @Override
    public List<Book> getBooksOrderedByAvgRate() {
        return bookRepository.findAllByOrderByAvgRateDesc().stream()
                .filter(book -> book.getAvgRate() != 0).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateBookStatus(BookUpdateStatusDto bookUpdateStatusDto) {
        Optional<Book> book = bookRepository.findByIdentifier(bookUpdateStatusDto.getBookIdentifier());
        if (book.isPresent()) {
            book.get().setBookStatus(BookStatus.valueOf(bookUpdateStatusDto.getNewBookStatus()));
            bookRepository.update(book.get());
        }
        if (BookStatus.AVAILABLE.toString().equals(bookUpdateStatusDto.getNewBookStatus())) {
            reservationsProducer.updateReservationStatus(
                    new ReservationUpdateStatusDto(RESERVATION_STATUS_FINISHED, bookUpdateStatusDto.getReservationId()));
        } else {
            reservationsProducer.updateReservationStatus(
                    new ReservationUpdateStatusDto(RESERVATION_STATUS_ACTIVE, bookUpdateStatusDto.getReservationId()));
        }
    }

    @Override
    @Transactional
    public String savePerformanceTest() {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 4000; i++) {
            UUID uuid = UUID.randomUUID();
            Book book = new Book();
            book.setTitle("title" + i);
            book.setAuthor("author" + i);
            book.setIdentifier(uuid.toString().substring(0, 10));
            book.setBookKind(BookKind.ACTION);
            book.setBookStatus(BookStatus.AVAILABLE);
            book.setReleaseDate(LocalDate.now());
            book.setOwnerUsername("admin");
            book.setAvgRate(5.0);
            book.setOpinions(Collections.emptySet());
            books.add(book);
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
        long startTime = System.nanoTime();
        PanacheQuery<Book> all = bookRepository.findAll();
        long duration = System.nanoTime() - startTime;

        return "reading books finished - " + duration + " ns, size: " + all.stream().count();
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
