package pp.books.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import pp.books.domain.Opinion;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class OpinionRepository implements PanacheRepository<Opinion> {

    public List<Opinion> findByBookId(long bookId) {
        return find("bookId", bookId).list();
    }

    public void save(Opinion opinion) {
        persist(opinion);
    }
}
