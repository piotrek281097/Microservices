package pp.books.service;



import pp.books.domain.Book;
import pp.books.domain.Opinion;
import pp.books.dto.BookUpdateStatusDto;
import pp.books.dto.OpinionDto;
import pp.books.dto.ReservationUpdateStatusDto;
import pp.books.enums.BookStatus;
import pp.books.repository.BookRepository;
import pp.books.repository.OpinionRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookServiceImpl implements BookService {

    private static final String ADMIN_USERNAME = "admin";

//    private final ReservationsProducer reservationsProducer; // kafka

    private final BookRepository bookRepository;

    private final OpinionRepository opinionRepository;

//    public BookServiceImpl(ReservationsProducer reservationsProducer, BookRepository bookRepository, OpinionRepository opinionRepository) {
//        this.reservationsProducer = reservationsProducer;
//        this.bookRepository = bookRepository;
//        this.opinionRepository = opinionRepository;
//    }


    public BookServiceImpl(BookRepository bookRepository, OpinionRepository opinionRepository) {
        this.bookRepository = bookRepository;
        this.opinionRepository = opinionRepository;
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        if (bookRepository.findByIdentifier(book.getIdentifier()) == null) {
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
        bookRepository.save(book); //update naprawic
        opinionRepository.save(opinion);
    }

    @Override
    public List<Book> getBooksOrderedByAvgRate() {
        return bookRepository.findAllByOrderByAvgRateDesc().stream()
                .filter(book -> book.getAvgRate() != 0).collect(Collectors.toList());
    }

    @Override
    public void updateBookStatus(BookUpdateStatusDto bookUpdateStatusDto) {
        Optional<Book> book = Optional.of(bookRepository.findByIdentifier(bookUpdateStatusDto.getBookIdentifier()));
        if (book.isPresent()) {
            book.get().setBookStatus(BookStatus.valueOf(bookUpdateStatusDto.getNewBookStatus()));
            bookRepository.save(book.get());
        }
//        KAFKA
//        if (BookStatus.AVAILABLE.toString().equals(bookUpdateStatusDto.getNewBookStatus())) {
//            reservationsProducer.sendMessage(
//                    new ReservationUpdateStatusDto("FINISHED", bookUpdateStatusDto.getReservationId()));
//        } else {
//            reservationsProducer.sendMessage(
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
