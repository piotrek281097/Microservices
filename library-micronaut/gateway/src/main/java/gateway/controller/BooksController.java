package gateway.controller;

import gateway.books.BooksOperations;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Controller("/")
public class BooksController {

    protected BooksOperations booksOperations;

    public BooksController(BooksOperations booksOperations) {
        this.booksOperations = booksOperations;
    }

    @Post("/books/add")
    @Secured({"ADMIN", "USER"})
    public HttpResponse addBook(@Body String book) {
        return booksOperations.addBook(book);
    }

    @Get("/books/classic-library")
    @Secured({"ADMIN", "USER"})
    public HttpResponse getClassicLibraryBooks() {
        return booksOperations.getClassicLibraryBooks();
    }

    @Get("/books/rental-service")
    @Secured({"ADMIN", "USER"})
    public HttpResponse getUserRentalServiceBooks() {
        return booksOperations.getUserRentalServiceBooks();
    }

    @Delete("/books/delete/{bookId}")
    @Secured({"ADMIN", "USER"})
    public HttpResponse deleteBookById(@PathVariable long bookId) {
        return booksOperations.deleteBookById(bookId);
    }
}
