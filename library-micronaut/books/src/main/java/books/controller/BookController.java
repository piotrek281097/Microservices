package books.controller;

import books.domain.Book;
import books.service.BookService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class BookController {

    protected final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Post("/books/add")
    public HttpResponse addBook(@Body Book book) {
        bookService.addBook(book);
        return HttpResponse.ok(Collections.singletonMap("msg", "OK"));
    }

    @Get("/books/classic-library")
    public HttpResponse<List<Book>> getClassicLibraryBooks() {
        return HttpResponse.ok(bookService.getClassicLibraryBooks());
    }

    @Get("/books/rental-service")
    public HttpResponse<List<Book>> getUserRentalServiceBooks() {
        return HttpResponse.ok(bookService.getUserRentalServiceBooks());
    }

    @Delete("/books/delete/{bookId}")
    public HttpResponse deleteBookById(@PathVariable long bookId) {
        bookService.deleteBookById(bookId);
        return HttpResponse.ok(Collections.singletonMap("msg", "OK"));
    }

}
