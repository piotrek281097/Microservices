package books.service;

import books.domain.Book;
import books.dto.BookUpdateStatusDto;
import books.dto.OpinionDto;

import java.util.List;

public interface BookService {

    void addBook(Book book);

    List<Book> getClassicLibraryBooks();

    List<Book> getUserRentalServiceBooks();

    void deleteBookById(long bookId);

    Book getBookById(long bookId);

    void updateBook(Book book);

    void addOpinion(OpinionDto opinion);

    List<Book> getBooksOrderedByAvgRate();

    void updateBookStatus(BookUpdateStatusDto bookUpdateStatusDto);
}
