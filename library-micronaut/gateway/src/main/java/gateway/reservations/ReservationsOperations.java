package gateway.reservations;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.PathVariable;

public interface ReservationsOperations {
    HttpResponse addReservation(@Body String reservation);

    HttpResponse getClassicLibraryReservations();

    HttpResponse getUserRentalServiceReservations();

    HttpResponse getClassicLibraryReservationsForUser(@PathVariable String username);

    HttpResponse getUserRentalServiceReservationsForUser(@PathVariable String username);

    HttpResponse updateReservation(@Body String reservation);
}
