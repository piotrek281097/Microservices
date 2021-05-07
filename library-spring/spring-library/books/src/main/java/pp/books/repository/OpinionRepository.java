package pp.books.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pp.books.domain.Opinion;

@Repository
public interface OpinionRepository extends CrudRepository<Opinion, Long> {

    Iterable<Opinion> findByBookId(long bookId);

}
