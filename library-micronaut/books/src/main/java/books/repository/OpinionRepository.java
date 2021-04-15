package books.repository;

import books.domain.Book;
import books.domain.Opinion;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;

@Repository
public interface OpinionRepository extends CrudRepository<Opinion, Long> {

    Iterable<Opinion> findByBookId(long bookId);

}
