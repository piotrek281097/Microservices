package books.service;

import books.domain.Book;
import books.repository.BookRepository;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class BookServiceImpl implements BookService {

    protected BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
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
}
