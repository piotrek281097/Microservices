package books.service;

import books.domain.Book;

import java.util.List;

public interface BookService {

    void addBook(Book book);

    List<Book> getClassicLibraryBooks();

    List<Book> getUserRentalServiceBooks();

    void deleteBookById(long bookId);
}
