package pp.users.service;

import io.vertx.ext.auth.User;
import pp.users.domain.UserData;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class UserDataServiceImpl implements UserDataService {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public void register(UserData userData) {
        em.persist(userData);
    }
}
