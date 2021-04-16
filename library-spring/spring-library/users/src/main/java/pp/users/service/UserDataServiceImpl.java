package pp.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pp.users.domain.UserData;
import pp.users.repository.UserDataRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserDataServiceImpl implements UserDataService {

    private UserDataRepository userDataRepository;

    public UserDataServiceImpl(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @Override
    public void register(UserData userData) {
        this.userDataRepository.save(userData);
    }

    @Override
    public Optional<UserData> findByUsername(String email) {
        return Optional.empty();
    }

    @Override
    public List<UserData> getAllUsers() {
        return null;
    }

    @Override
    public void deleteUserById(long userId) {

    }

    @Override
    public UserData getUserById(long userId) {
        return null;
    }

    @Override
    public UserData getUserByUsername(String username) {
        return null;
    }

    @Override
    public void updateUser(UserData userData) {

    }
}
