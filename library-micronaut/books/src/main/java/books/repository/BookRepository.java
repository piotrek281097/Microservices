package books.repository;

import books.domain.Book;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    Iterable<Book> findByOwnerUsername(String ownerUsername);

    Iterable<Book> findByOwnerUsernameNotEquals(String ownerUsername);

    Optional<Book> findByIdentifier(String identifier);

    Iterable<Book> listOrderByAvgRateDesc();

}
