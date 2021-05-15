package pp.users.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import pp.users.domain.UserData;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserDataRepository implements PanacheRepository<UserData> {

    public UserData findByUsername(String username){
        return find("username", username).firstResult();
    }

    public void save(UserData userData) {
        persist(userData);
    }
}
