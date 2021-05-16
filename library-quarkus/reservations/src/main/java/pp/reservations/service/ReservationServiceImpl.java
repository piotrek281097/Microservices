package pp.reservations.service;

import pp.reservations.domain.Reservation;
import pp.reservations.dto.BookUpdateStatusDto;
import pp.reservations.dto.ReservationUpdateStatusDto;
import pp.reservations.enums.ReservationStatus;
import pp.reservations.repository.ReservationRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped

public class ReservationServiceImpl implements ReservationService {

    private static final String ADMIN_USERNAME = "admin";

    private static final String BOOK_STATUS_AVAILABLE = "AVAILABLE";

    private final ReservationRepository reservationRepository;

//    private final BooksProducer booksProducer; kafka

//    public ReservationServiceImpl(ReservationRepository reservationRepository, BooksProducer booksProducer) {
//        this.reservationRepository = reservationRepository;
//        this.booksProducer = booksProducer;
//    }


    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    @Transactional
    public void addReservation(Reservation reservation) {
        UUID uuid = UUID.randomUUID();
        reservation.setReservationIdentifier(uuid.toString().substring(0,10));
        long reservationId = reservationRepository.save(reservation);

//        booksProducer.sendMessage(new BookUpdateStatusDto(reservation.getBookIdentifier(), "RESERVED",
//                reservationId));
    }

    @Override
    public List<Reservation> getClassicLibraryReservations() {
        return reservationRepository.findByOwnerUsername(ADMIN_USERNAME);
    }

    @Override
    public List<Reservation> getUserRentalServiceReservations() {
        return reservationRepository.findByOwnerUsernameNotLike(ADMIN_USERNAME);
    }

    @Override
    public List<Reservation> getClassicLibraryReservationsForUser(String username) {
        return reservationRepository.findByOwnerUsernameAndBorrowerUsername(ADMIN_USERNAME, username);
    }

    @Override
    public List<Reservation> getUserRentalServiceReservationsForUser(String username) {
        return reservationRepository.findByOwnerUsernameOrBorrowerUsername(username, username).stream()
                .filter(reservation -> !reservation.getOwnerUsername().equals(ADMIN_USERNAME))
                .collect(Collectors.toList());
    }

    @Override
    public void updateReservation(Reservation reservation) {
//        kafka
//        booksProducer.sendMessage(new BookUpdateStatusDto(reservation.getBookIdentifier(), BOOK_STATUS_AVAILABLE,
//                reservation.getId()));
    }

    @Override
    public void updateReservationStatus(ReservationUpdateStatusDto reservationUpdateStatusDto) {
        Optional<Reservation> reservation = reservationRepository.findByIdOptional(reservationUpdateStatusDto.getReservationId());
        if (reservation.isPresent()) {
            reservation.get().setReservationStatus(ReservationStatus.valueOf(reservationUpdateStatusDto.getNewReservationStatus()));
            reservationRepository.save(reservation.get());
        }
    }
}
