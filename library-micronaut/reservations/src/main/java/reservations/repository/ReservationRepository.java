package reservations.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import reservations.domain.Reservation;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    List<Reservation> findByOwnerUsername(String ownerUsername);

    List<Reservation> findByOwnerUsernameNotEquals(String ownerUsername);

    List<Reservation> findByOwnerUsernameAndBorrowerUsername(String ownerUsername, String borrowerUsername);

    List<Reservation> findByOwnerUsernameOrBorrowerUsername(String ownerUsername, String borrowerUsername);

}
