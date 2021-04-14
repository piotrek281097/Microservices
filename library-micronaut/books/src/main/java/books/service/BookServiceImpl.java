package books.service;

import books.domain.Book;
import books.domain.Opinion;
import books.dto.OpinionDto;
import books.enums.BookStatus;
import books.exception.BookIdentifierAlreadyExists;
import books.repository.BookRepository;
import books.repository.OpinionRepository;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class BookServiceImpl implements BookService {

    protected BookRepository bookRepository;

    protected OpinionRepository opinionRepository;

    public BookServiceImpl(BookRepository bookRepository, OpinionRepository opinionRepository) {
        this.bookRepository = bookRepository;
        this.opinionRepository = opinionRepository;
    }

    @Override
    public void addBook(Book book) {
        if (bookRepository.findByIdentifier(book.getIdentifier()).isEmpty()) {
            bookRepository.save(book);
        } else {
            throw new BookIdentifierAlreadyExists();
        }

    }

    @Override
    public List<Book> getClassicLibraryBooks() {
        return (List<Book>) bookRepository.findByOwnerUsername("admin");

    }

    @Override
    public List<Book> getUserRentalServiceBooks() {
        return (List<Book>) bookRepository.findByOwnerUsernameNotEquals("admin");
    }

    @Override
    public void deleteBookById(long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public Book getBookById(long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        return book.orElse(null);
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.update(book);
    }

    @Override
    public void changeBookStatus(String identifier) {
        Optional<Book> book = bookRepository.findByIdentifier(identifier);
        if (book.isPresent()) {
            book.get().setBookStatus(BookStatus.AVAILABLE);
            bookRepository.update(book.get());
        }
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
        bookRepository.update(book);
        opinionRepository.save(opinion);
    }

    @Override
    public List<Book> getBooksOrderedByAvgRate() {
        return ((List<Book>) bookRepository.listOrderByAvgRateDesc()).stream()
                .filter(book -> book.getAvgRate() != 0).collect(Collectors.toList());
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
