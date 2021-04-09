package gateway.controller;

import gateway.books.BooksOperations;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.util.Collections;

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

    @Get("/books/{bookId}")
    @Secured("USER")
    public HttpResponse getBookById(@PathVariable long bookId) {
        return booksOperations.getBookById(bookId);
    }

    @Post("/books/update")
    @Secured({"ADMIN", "USER"})
    public HttpResponse updateBook(@Body String book) {
        return booksOperations.updateBook(book);
    }

    @Post("/books/change-status/{identifier}")
    @Secured({"ADMIN", "USER"})
    public HttpResponse changeBookStatus(@PathVariable String identifier) {
        return booksOperations.changeBookStatus(identifier);
    }

    @Post("/books/add-opinion")
    @Secured("USER")
    public HttpResponse addOpinion(@Body String opinionDto) {
        return booksOperations.addOpinion(opinionDto);
    }
}
