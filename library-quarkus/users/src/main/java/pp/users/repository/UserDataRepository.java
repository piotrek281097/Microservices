package pp.users.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import pp.users.domain.UserData;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class UserDataRepository implements PanacheRepository<UserData> {

    @Inject
    EntityManager entityManager;

    public UserData findByUsername(String username){
        return find("username", username).firstResult();
    }

    public void save(UserData userData) {
        persist(userData);
    }

    public void update(UserData userData) {
        entityManager.merge(userData);
    }
}
