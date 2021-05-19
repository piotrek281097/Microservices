package gateway.books;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Recoverable;


@Client(id = "books")
@Recoverable(api = BooksOperations.class)
public interface BooksClient extends BooksOperations {

    @Post("/books/add")
    HttpResponse addBook(@Body String book);

    @Get("/books/classic-library")
    HttpResponse getClassicLibraryBooks();

    @Get("/books/rental-service")
    HttpResponse getUserRentalServiceBooks();

    @Delete("/books/delete/{bookId}")
    HttpResponse deleteBookById(@PathVariable long bookId);

    @Get("/books/{bookId}")
    HttpResponse getBookById(@PathVariable long bookId);

    @Post("/books/update")
    HttpResponse updateBook(@Body String book);

    @Post("/books/add-opinion")
    HttpResponse addOpinion(@Body String opinionDto);

    @Get("/books/ratings")
    HttpResponse getBooksOrderedByAvgRate();
}
