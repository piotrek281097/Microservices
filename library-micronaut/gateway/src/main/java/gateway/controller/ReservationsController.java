package gateway.controller;

import gateway.reservations.ReservationsOperations;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;

import java.util.List;

@Controller("/")
public class ReservationsController {

    protected ReservationsOperations reservationsOperations;

    public ReservationsController(ReservationsOperations reservationsOperations) {
        this.reservationsOperations = reservationsOperations;
    }

    @Post("/reservations/add")
    @Secured("USER")
    public HttpResponse addReservations(@Body String reservation) {
        return reservationsOperations.addReservation(reservation);
    }

    @Get("/reservations/classic-library")
    @Secured("ADMIN")
    public HttpResponse getClassicLibraryReservations() {
        return reservationsOperations.getClassicLibraryReservations();
    }

    @Get("/reservations/rental-service")
    @Secured("ADMIN")
    public HttpResponse getUserRentalServiceReservations() {
        return reservationsOperations.getUserRentalServiceReservations();
    }

    @Get("/reservations/classic-library/{username}")
    @Secured("USER")
    public HttpResponse getClassicLibraryReservationsForUser(@PathVariable String username) {
        return reservationsOperations.getClassicLibraryReservationsForUser(username);
    }

    @Get("/reservations/rental-service/{username}")
    @Secured("USER")
    public HttpResponse getUserRentalServiceBooksForUser(@PathVariable String username) {
        return reservationsOperations.getUserRentalServiceReservationsForUser(username);
    }

    @Post("/reservations/update")
    @Secured({"ADMIN", "USER"})
    public HttpResponse updateReservation(@Body String reservation) {
        return reservationsOperations.updateReservation(reservation);
    }
}
