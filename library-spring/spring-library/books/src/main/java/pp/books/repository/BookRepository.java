package pp.books.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pp.books.domain.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
//
//    Iterable<Book> findByOwnerUsername(String ownerUsername);
//
//    Iterable<Book> findByOwnerUsernameNotEquals(String ownerUsername);
//
//    Optional<Book> findByIdentifier(String identifier);
//
//    Iterable<Book> listOrderByAvgRateDesc();

}
