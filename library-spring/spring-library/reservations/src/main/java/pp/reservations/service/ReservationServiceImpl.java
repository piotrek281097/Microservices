package pp.reservations.service;

import org.springframework.stereotype.Service;
import pp.reservations.domain.Reservation;
import pp.reservations.dto.BookUpdateStatusDto;
import pp.reservations.dto.ReservationUpdateStatusDto;
import pp.reservations.enums.ReservationStatus;
import pp.reservations.kafka.BooksProducer;
import pp.reservations.repository.ReservationRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
//
//    public ReservationServiceImpl(ReservationRepository reservationRepository) {
//        this.reservationRepository = reservationRepository;
//    }

    private final BooksProducer booksProducer;

    public ReservationServiceImpl(ReservationRepository reservationRepository, BooksProducer booksProducer) {
        this.reservationRepository = reservationRepository;
        this.booksProducer = booksProducer;
    }

    @Override
    public void addReservation(Reservation reservation) {
        UUID uuid = UUID.randomUUID();
        reservation.setReservationIdentifier(uuid.toString().substring(0,10));
        Reservation savedReservation = reservationRepository.save(reservation);

        booksProducer.sendMessage(new BookUpdateStatusDto(reservation.getBookIdentifier(), "RESERVED",
                savedReservation.getId()));

//        System.out.println("MAKE RESERVATION");
    }

    @Override
    public List<Reservation> getClassicLibraryReservations() {
        return reservationRepository.findByOwnerUsername("admin");
    }

    @Override
    public List<Reservation> getUserRentalServiceReservations() {
        return reservationRepository.findByOwnerUsernameNotLike("admin");
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
//        booksClient.updateBookStatus("key", new BookUpdateStatusDto(reservation.getBookIdentifier(), "AVAILABLE",
//                reservation.getId()));
    }

    @Override
    public void updateReservationStatus(ReservationUpdateStatusDto reservationUpdateStatusDto) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationUpdateStatusDto.getReservationId());
        if (reservation.isPresent()) {
            reservation.get().setReservationStatus(ReservationStatus.valueOf(reservationUpdateStatusDto.getNewReservationStatus()));
            reservationRepository.save(reservation.get());
        }
    }
}
