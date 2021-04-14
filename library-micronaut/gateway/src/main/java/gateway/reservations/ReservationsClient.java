package gateway.reservations;

import gateway.books.BooksOperations;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Recoverable;

@Client(id = "reservations")
@Recoverable(api = ReservationsOperations.class)
public interface ReservationsClient extends ReservationsOperations {

    @Post("/reservations/add")
    HttpResponse addReservation(@Body String reservation);

    @Get("/reservations/classic-library")
    HttpResponse getClassicLibraryReservations();

    @Get("/reservations/rental-service")
    HttpResponse getUserRentalServiceReservations();

    @Get("/reservations/classic-library/{username}")
    HttpResponse getClassicLibraryReservationsForUser(@PathVariable String username);

    @Get("/reservations/rental-service/{username}")
    HttpResponse getUserRentalServiceReservationsForUser(@PathVariable String username);

    @Post("/reservations/update")
    HttpResponse updateReservation(@Body String reservation);
}
