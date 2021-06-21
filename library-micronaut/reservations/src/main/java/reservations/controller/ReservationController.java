package reservations.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
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
    @Secured("ROLE_USER")
    public HttpResponse addReservation(@Body Reservation reservation) {
        reservationService.addReservation(reservation);
        return HttpResponse.ok(Collections.singletonMap("msg", "OK"));
    }

    @Get("/reservations/classic-library")
    @Secured("ROLE_ADMIN")
    public HttpResponse<List<Reservation>> getClassicLibraryReservations() {
        return HttpResponse.ok(reservationService.getClassicLibraryReservations());
    }

    @Get("/reservations/rental-service")
    @Secured("ROLE_ADMIN")
    public HttpResponse<List<Reservation>> getUserRentalServiceReservations() {
        return HttpResponse.ok(reservationService.getUserRentalServiceReservations());
    }

    @Get("/reservations/classic-library/{username}")
    @Secured("ROLE_USER")
    public HttpResponse<List<Reservation>> getClassicLibraryReservationsForUser(@PathVariable String username) {
        return HttpResponse.ok(reservationService.getClassicLibraryReservationsForUser(username));
    }

    @Get("/reservations/rental-service/{username}")
    @Secured("ROLE_USER")
    public HttpResponse<List<Reservation>> getUserRentalServiceBooksForUser(@PathVariable String username) {
        return HttpResponse.ok(reservationService.getUserRentalServiceReservationsForUser(username));
    }

    @Post("/reservations/update")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public HttpResponse updateReservation(@Body Reservation reservation) {
        reservationService.updateReservation(reservation);
        return HttpResponse.ok(Collections.singletonMap("msg", "OK"));
    }

    @Get(value = "/save-test-data", produces = MediaType.TEXT_PLAIN)
    @Secured(SecurityRule.IS_ANONYMOUS)
    public String savePerformanceTest() {
        return reservationService.saveTestData();
    }
}
