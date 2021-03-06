package reservations.service;

import reservations.domain.Reservation;
import reservations.dto.ReservationUpdateStatusDto;

import java.util.List;

public interface ReservationService {

    void addReservation(Reservation reservation);

    List<Reservation> getClassicLibraryReservations();

    List<Reservation> getUserRentalServiceReservations();

    List<Reservation> getClassicLibraryReservationsForUser(String username);

    List<Reservation> getUserRentalServiceReservationsForUser(String username);

    void updateReservation(Reservation reservation);

    void updateReservationStatus(ReservationUpdateStatusDto reservationUpdateStatusDto);

    String saveTestData();
}
