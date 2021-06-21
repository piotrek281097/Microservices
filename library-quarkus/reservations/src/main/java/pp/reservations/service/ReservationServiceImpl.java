package pp.reservations.service;

import pp.reservations.domain.Reservation;
import pp.reservations.dto.BookUpdateStatusDto;
import pp.reservations.dto.ReservationUpdateStatusDto;
import pp.reservations.enums.ReservationStatus;
import pp.reservations.kafka.BooksToAvailableProducer;
import pp.reservations.kafka.BooksToReservedProducer;
import pp.reservations.repository.ReservationRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class ReservationServiceImpl implements ReservationService {

    private static final String ADMIN_USERNAME = "admin";

    private static final String BOOK_STATUS_AVAILABLE = "AVAILABLE";
    public static final String BOOK_STATUS_RESERVED = "RESERVED";

    private final ReservationRepository reservationRepository;

    private final BooksToReservedProducer booksToReservedProducer;

    private final BooksToAvailableProducer booksToAvailableProducer;

    public ReservationServiceImpl(ReservationRepository reservationRepository, BooksToReservedProducer booksToReservedProducer, BooksToAvailableProducer booksToAvailableProducer) {
        this.reservationRepository = reservationRepository;
        this.booksToReservedProducer = booksToReservedProducer;
        this.booksToAvailableProducer = booksToAvailableProducer;
    }

    @Override
    @Transactional
    public void addReservation(Reservation reservation) {
        UUID uuid = UUID.randomUUID();
        reservation.setReservationIdentifier(uuid.toString().substring(0,10));
        long reservationId = reservationRepository.save(reservation);

        booksToReservedProducer.updateBookReserved(new BookUpdateStatusDto(reservation.getBookIdentifier(), BOOK_STATUS_RESERVED,
                reservationId));
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
        booksToAvailableProducer.updateBookAvailable(new BookUpdateStatusDto(reservation.getBookIdentifier(), BOOK_STATUS_AVAILABLE,
                reservation.getId()));
    }

    @Override
    @Transactional
    public void updateReservationStatus(ReservationUpdateStatusDto reservationUpdateStatusDto) {
        Optional<Reservation> reservation = reservationRepository.findByIdOptional(reservationUpdateStatusDto.getReservationId());
        if (reservation.isPresent()) {
            reservation.get().setReservationStatus(ReservationStatus.valueOf(reservationUpdateStatusDto.getNewReservationStatus()));
            reservationRepository.update(reservation.get());
        }
    }

    @Override
    @Transactional
    public String saveTestData() {
        List<Reservation> reservations = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            UUID uuid = UUID.randomUUID();
            Reservation reservation = new Reservation();
            reservation.setReservationIdentifier(uuid.toString().substring(0, 10));
            reservation.setStartDate(LocalDate.now());
            reservation.setEndDate(LocalDate.now());
            reservation.setReservationStatus(ReservationStatus.ACTIVE);
            reservation.setBookTitle("title" + i);
            reservation.setBookIdentifier(uuid.toString().substring(0, 10));
            reservation.setBorrowerUsername("username" + i);
            reservation.setOwnerUsername("admin");
            reservations.add(reservation);
        }

        long startTime = System.currentTimeMillis();
        for (Reservation reservation : reservations) {
            reservationRepository.save(reservation);
        }
        long duration = System.currentTimeMillis() - startTime;
        return "saving reservations finished - " + duration + " ms";
    }
}
