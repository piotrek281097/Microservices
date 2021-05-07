package pp.books.service;



import org.springframework.stereotype.Service;
import pp.books.domain.Book;
import pp.books.domain.Opinion;
import pp.books.dto.BookUpdateStatusDto;
import pp.books.dto.OpinionDto;
import pp.books.dto.ReservationUpdateStatusDto;
import pp.books.enums.BookStatus;
import pp.books.exception.BookIdentifierAlreadyExists;
import pp.books.repository.BookRepository;
import pp.books.repository.OpinionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

//    protected ReservationsClient reservationsClient; // kafka

    private BookRepository bookRepository;

    private OpinionRepository opinionRepository;
//
//    public BookServiceImpl(ReservationsClient reservationsClient, BookRepository bookRepository, OpinionRepository opinionRepository) {
//        this.reservationsClient = reservationsClient;
//        this.bookRepository = bookRepository;
//        this.opinionRepository = opinionRepository;
//    }


    public BookServiceImpl(BookRepository bookRepository, OpinionRepository opinionRepository) {
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
        return (List<Book>) bookRepository.findByOwnerUsername("admin");
    }

    @Override
    public List<Book> getUserRentalServiceBooks() {
        return (List<Book>) bookRepository.findByOwnerUsernameNotLike("admin");
    }

    @Override
    public void deleteBookById(long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent() && BookStatus.AVAILABLE.equals(optionalBook.get().getBookStatus())) {
            opinionRepository.findByBookId(bookId).forEach(opinion -> opinionRepository.delete(opinion));
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

        // kafka

//        if (BookStatus.AVAILABLE.toString().equals(bookUpdateStatusDto.getNewBookStatus())) {
//            reservationsClient.updateReservationStatus("key",
//                    new ReservationUpdateStatusDto("FINISHED", bookUpdateStatusDto.getReservationId()));
//        } else {
//            reservationsClient.updateReservationStatus("key",
//                    new ReservationUpdateStatusDto("ACTIVE", bookUpdateStatusDto.getReservationId()));
//        }
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
