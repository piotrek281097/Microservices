package pp.reservations.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import pp.reservations.domain.Reservation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class ReservationRepository implements PanacheRepository<Reservation> {

    @Inject
    EntityManager entityManager;

    public long save(Reservation reservation) {
        persist(reservation);
        flush();
        return reservation.getId();
//        return find("reservationIdentifier", reservation.getReservationIdentifier()).firstResult();
    }

    public void update(Reservation reservation) {
        entityManager.merge(reservation);
    }

    public List<Reservation> findByOwnerUsername(String ownerUsername) {
        return find("ownerUsername", ownerUsername).list();
    }

    public List<Reservation> findByOwnerUsernameNotLike(String ownerUsername) {
        try (Stream<Reservation> reservations = Reservation.streamAll()) {
            return reservations
                    .filter(reservation -> !ownerUsername.equals(reservation.getOwnerUsername()) )
                    .collect(Collectors.toList());
        }
    }

    public List<Reservation> findByOwnerUsernameAndBorrowerUsername(String adminUsername, String username) {
        try (Stream<Reservation> reservations = Reservation.streamAll()) {
            return reservations
                    .filter(reservation ->
                            adminUsername.equals(reservation.getOwnerUsername()) && username.equals(reservation.getBorrowerUsername()))
                    .collect(Collectors.toList());
        }
    }

    public List<Reservation> findByOwnerUsernameOrBorrowerUsername(String ownerUsername, String borrowerUsername) {
        try (Stream<Reservation> reservations = Reservation.streamAll()) {
            return reservations
                    .filter(reservation ->
                            ownerUsername.equals(reservation.getOwnerUsername()) || borrowerUsername.equals(reservation.getBorrowerUsername()))
                    .collect(Collectors.toList());
        }
    }
}
