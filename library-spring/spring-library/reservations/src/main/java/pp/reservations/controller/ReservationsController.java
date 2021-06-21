package pp.reservations.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pp.reservations.domain.Reservation;
import pp.reservations.service.ReservationService;

import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
public class ReservationsController {

    private ReservationService reservationService;

    public ReservationsController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reservations/add")
    public ResponseEntity<?> addReservation(@RequestBody Reservation reservation) {
        reservationService.addReservation(reservation);
        return new ResponseEntity<>(Collections.singletonMap("msg", "OK"), HttpStatus.OK);
    }

    @GetMapping("/reservations/classic-library")
    public ResponseEntity<List<Reservation>> getClassicLibraryReservations() {
        return new ResponseEntity<>(reservationService.getClassicLibraryReservations(), HttpStatus.OK);
    }

    @GetMapping("/reservations/rental-service")
    public ResponseEntity<List<Reservation>> getUserRentalServiceReservations() {
        return new ResponseEntity<>(reservationService.getUserRentalServiceReservations(), HttpStatus.OK);
    }

    @GetMapping("/reservations/classic-library/{username}")
    public ResponseEntity<List<Reservation>> getClassicLibraryReservationsForUser(@PathVariable String username) {
        return new ResponseEntity<>(reservationService.getClassicLibraryReservationsForUser(username), HttpStatus.OK);
    }

    @GetMapping("/reservations/rental-service/{username}")
    public ResponseEntity<List<Reservation>> getUserRentalServiceReservationsForUser(@PathVariable String username) {
        return new ResponseEntity<>(reservationService.getUserRentalServiceReservationsForUser(username), HttpStatus.OK);
    }

    @PostMapping("/reservations/update")
    public ResponseEntity<?> updateReservation(@RequestBody Reservation reservation) {
        reservationService.updateReservation(reservation);
        return new ResponseEntity<>(Collections.singletonMap("msg", "OK"), HttpStatus.OK);
    }

    @GetMapping(value = "/save-test-data", produces = MediaType.TEXT_PLAIN_VALUE)
    public String saveTestData() {
        return reservationService.saveTestData();
    }
}
