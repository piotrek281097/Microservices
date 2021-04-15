package gateway.books;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.PathVariable;

public interface BooksOperations {

    HttpResponse addBook(@Body String book);

    HttpResponse getClassicLibraryBooks();

    HttpResponse getUserRentalServiceBooks();

    HttpResponse deleteBookById(@PathVariable long bookId);

    HttpResponse getBookById(@PathVariable long bookId);

    HttpResponse updateBook(@Body String book);

    HttpResponse addOpinion(@Body String opinionDto);

    HttpResponse getBooksOrderedByAvgRate();
}
