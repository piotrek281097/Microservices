package gateway.controller;

import gateway.books.BooksOperations;
import gateway.exception.BooksException;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.security.annotation.Secured;

@Controller("/")
public class BooksController {

    protected BooksOperations booksOperations;

    public BooksController(BooksOperations booksOperations) {
        this.booksOperations = booksOperations;
    }

    @Post("/books/add")
    @Secured({"ADMIN", "USER"})
    public HttpResponse addBook(@Body String book) {
        try {
            return booksOperations.addBook(book);
        } catch (HttpClientResponseException exception) {
            throw new BooksException(exception.getMessage());
        }
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

    @Post("/books/add-opinion")
    @Secured("USER")
    public HttpResponse addOpinion(@Body String opinionDto) {
        return booksOperations.addOpinion(opinionDto);
    }

    @Get("/books/ratings")
    @Secured({"ADMIN", "USER"})
    public HttpResponse getBooksOrderedByAvgRate() {
        return booksOperations.getBooksOrderedByAvgRate();
    }
}
