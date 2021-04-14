package reservations.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import reservations.domain.Reservation;
import reservations.service.ReservationService;

import java.util.Collections;
import java.util.List;

@Controller
public class ReservationController {

    protected ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Post("/reservations/add")
    @Secured("USER")
    public HttpResponse addReservation(@Body Reservation reservation) {
        reservationService.addReservation(reservation);
        return HttpResponse.ok(Collections.singletonMap("msg", "OK"));
    }

    @Get("/reservations/classic-library")
    @Secured("ADMIN")
    public HttpResponse<List<Reservation>> getClassicLibraryReservations() {
        return HttpResponse.ok(reservationService.getClassicLibraryReservations());
    }

    @Get("/reservations/rental-service")
    @Secured("ADMIN")
    public HttpResponse<List<Reservation>> getUserRentalServiceReservations() {
        return HttpResponse.ok(reservationService.getUserRentalServiceReservations());
    }

    @Get("/reservations/classic-library/{username}")
    @Secured("USER")
    public HttpResponse<List<Reservation>> getClassicLibraryReservationsForUser(@PathVariable String username) {
        return HttpResponse.ok(reservationService.getClassicLibraryReservationsForUser(username));
    }

    @Get("/reservations/rental-service/{username}")
    @Secured("USER")
    public HttpResponse<List<Reservation>> getUserRentalServiceBooksForUser(@PathVariable String username) {
        return HttpResponse.ok(reservationService.getUserRentalServiceReservationsForUser(username));
    }

    @Post("/reservations/update")
    @Secured({"ADMIN", "USER"})
    public HttpResponse updateReservation(@Body Reservation reservation) {
        reservationService.updateReservation(reservation);
        return HttpResponse.ok(Collections.singletonMap("msg", "OK"));
    }
}
