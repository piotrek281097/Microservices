package reservations.service;

import reservations.domain.Reservation;
import reservations.dto.BookUpdateStatusDto;
import reservations.dto.ReservationUpdateStatusDto;
import reservations.enums.ReservationStatus;
import reservations.kafka.BooksClient;
import reservations.repository.ReservationRepository;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Singleton
public class ReservationServiceImpl implements ReservationService {

    protected ReservationRepository reservationRepository;

    protected BooksClient booksClient;

    public ReservationServiceImpl(ReservationRepository reservationRepository, BooksClient booksClient) {
        this.reservationRepository = reservationRepository;
        this.booksClient = booksClient;
    }

    @Override
    public void addReservation(Reservation reservation) {
        UUID uuid = UUID.randomUUID();
        reservation.setReservationIdentifier(uuid.toString().substring(0, 10));
        Reservation savedReservation = reservationRepository.save(reservation);

        booksClient.updateBookStatus("key", new BookUpdateStatusDto(reservation.getBookIdentifier(), "RESERVED",
                savedReservation.getId()));
    }

    @Override
    public List<Reservation> getClassicLibraryReservations() {
        return reservationRepository.findByOwnerUsername("admin");
    }


    @Override
    public List<Reservation> getUserRentalServiceReservations() {
        return reservationRepository.findByOwnerUsernameNotEquals("admin");
    }

    @Override
    public List<Reservation> getClassicLibraryReservationsForUser(String username) {
        return reservationRepository.findByOwnerUsernameAndBorrowerUsername("admin", username);
    }

    @Override
    public List<Reservation> getUserRentalServiceReservationsForUser(String username) {
        return reservationRepository.findByOwnerUsernameOrBorrowerUsername(username, username).stream()
                .filter(reservation -> !reservation.getOwnerUsername().equals("admin"))
                .collect(Collectors.toList());
    }

    @Override
    public void updateReservation(Reservation reservation) {
        booksClient.updateBookStatus("key", new BookUpdateStatusDto(reservation.getBookIdentifier(), "AVAILABLE",
                reservation.getId()));
    }

    @Override
    public void updateReservationStatus(ReservationUpdateStatusDto reservationUpdateStatusDto) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationUpdateStatusDto.getReservationId());
        if (reservation.isPresent()) {
            reservation.get().setReservationStatus(ReservationStatus.valueOf(reservationUpdateStatusDto.getNewReservationStatus()));
            reservationRepository.update(reservation.get());
        }
    }
}
