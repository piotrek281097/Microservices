package pp.books.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import pp.books.domain.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class BookRepository implements PanacheRepository<Book> {

    @Inject
    EntityManager entityManager;

    public Optional<Book> findByIdentifier(String identifier){
        return find("identifier", identifier).firstResultOptional();
    }

    public void save(Book book) {
        persist(book);
    }

    public void update(Book book) {
        entityManager.merge(book);
//        persist(book);
    }

    public List<Book> findByOwnerUsername(String ownerUsername) {
        return find("ownerUsername", ownerUsername).list();
    }

    public List<Book> findByOwnerUsernameNotLike(String ownerUsername) {
        try (Stream<Book> books = Book.streamAll()) {
            return books
                    .filter(book -> !ownerUsername.equals(book.getOwnerUsername()) )
                    .collect(Collectors.toList());
        }
    }

    public List<Book> findAllByOrderByAvgRateDesc() {
        String jql = "Select b from Book as b order by b.avgRate desc";
        Query barQuery = entityManager.createQuery(jql);
        return barQuery.getResultList();
    }
}

