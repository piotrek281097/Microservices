package pp.users.service;

import pp.users.domain.UserData;
import pp.users.repository.UserDataRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class UserDataServiceImpl implements UserDataService {

    @Inject
    UserDataRepository userDataRepository;

    @Override
    @Transactional
    public void register(UserData userData) {
        UserData existingUserByUsername = UserData.find("username", userData.getUsername()).firstResult();
        if (existingUserByUsername == null) {
            UserData existingUserByEmail = UserData.find("email", userData.getEmail()).firstResult();
            if (existingUserByEmail == null) {
                userDataRepository.save(userData);
            } else {
                throw new WebApplicationException(Response.status(400).entity("Email is already taken").build());
            }
        } else {
            throw new WebApplicationException(Response.status(409).entity("Username is already taken").build());
        }
    }

    @Override
    public UserData findByUsername(String username) {
        return userDataRepository.findByUsername(username);
    }

    @Override
    public List<UserData> getAllUsers() {
        return userDataRepository.listAll();
    }

    @Override
    public void deleteUserById(long userId) {
        userDataRepository.deleteById(userId);
    }

    @Override
    public UserData getUserById(long userId) {
        return userDataRepository.findById(userId);
    }

    @Override
    public UserData getUserByUsername(String username) {
        return userDataRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void updateUser(UserData userData) {
        userDataRepository.update(userData);
    }
}
