package pp.users.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pp.users.domain.UserData;
import pp.users.repository.UserDataRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserDataServiceImpl implements UserDataService {

    private UserDataRepository userDataRepository;

    private PasswordEncoder encoder;

    public UserDataServiceImpl(UserDataRepository userDataRepository, PasswordEncoder encoder) {
        this.userDataRepository = userDataRepository;
        this.encoder = encoder;
    }

    @Override
    public void register(UserData userData) {
        userData.setPassword(encoder.encode(userData.getPassword()));
        this.userDataRepository.save(userData);
    }

    @Override
    public Optional<UserData> findByUsername(String username) {
        return userDataRepository.findByUsername(username);
    }

    @Override
    public List<UserData> getAllUsers() {
        return (List<UserData>) userDataRepository.findAll();
    }

    @Override
    public void deleteUserById(long userId) {
        userDataRepository.deleteById(userId);
    }

    @Override
    public UserData getUserById(long userId) {
        Optional<UserData> user = userDataRepository.findById(userId);
        return user.orElse(null);
    }

    @Override
    public UserData getUserByUsername(String username) {
        Optional<UserData> user = userDataRepository.findByUsername(username);
        return user.orElse(null);
    }

    @Override
    public void updateUser(UserData userData) {
        userDataRepository.save(userData);
    }
}
