package books.controller;

import books.domain.Book;
import books.dto.OpinionDto;
import books.service.BookService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;

import java.util.Collections;
import java.util.List;

@Controller
public class BookController {

    protected final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Post("/books/add")
    @Secured({"ADMIN", "USER"})
    public HttpResponse addBook(@Body Book book) {
        bookService.addBook(book);
        return HttpResponse.ok(Collections.singletonMap("msg", "OK"));
    }

    @Get("/books/classic-library")
    @Secured({"ADMIN", "USER"})
    public HttpResponse<List<Book>> getClassicLibraryBooks() {
        return HttpResponse.ok(bookService.getClassicLibraryBooks());
    }

    @Get("/books/rental-service")
    @Secured({"ADMIN", "USER"})
    public HttpResponse<List<Book>> getUserRentalServiceBooks() {
        return HttpResponse.ok(bookService.getUserRentalServiceBooks());
    }

    @Delete("/books/delete/{bookId}")
    @Secured({"ADMIN", "USER"})
    public HttpResponse deleteBookById(@PathVariable long bookId) {
        bookService.deleteBookById(bookId);
        return HttpResponse.ok(Collections.singletonMap("msg", "OK"));
    }

    @Get("/books/{bookId}")
    @Secured("USER")
    public HttpResponse<Book> getBookById(@PathVariable long bookId) {
        return HttpResponse.ok(bookService.getBookById(bookId));
    }

    @Post("/books/update")
    @Secured({"ADMIN", "USER"})
    public HttpResponse updateBook(@Body Book book) {
        bookService.updateBook(book);
        return HttpResponse.ok(Collections.singletonMap("msg", "OK"));
    }

    @Post("/books/add-opinion")
    @Secured("USER")
    public HttpResponse addOpinion(@Body OpinionDto opinionDto) {
        bookService.addOpinion(opinionDto);
        return HttpResponse.ok(Collections.singletonMap("msg", "OK"));
    }

    @Get("/books/ratings")
    @Secured({"ADMIN", "USER"})
    public HttpResponse<List<Book>> getBooksOrderedByAvgRate() {
        return HttpResponse.ok(bookService.getBooksOrderedByAvgRate());
    }
}

