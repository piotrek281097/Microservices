package registration.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import registration.domain.UserData;
import registration.repository.UserDataRepository;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class UserDataServiceImpl implements UserDataService {

    protected final UserDataRepository userDataRepository;

    protected final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    public UserDataServiceImpl(UserDataRepository userDataRepository, PasswordEncoder passwordEncoder) {
        this.userDataRepository = userDataRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserData userData) {
        Optional<UserData> userFoundByEmail = userDataRepository.findByEmail(userData.getEmail());
        if (userFoundByEmail.isEmpty()) {
            Optional<UserData> userFoundUsername = userDataRepository.findByUsername(userData.getUsername());
            if (userFoundUsername.isEmpty()) {
                final String encodedPassword = passwordEncoder.encode(userData.getPassword());
                userData.setPassword(encodedPassword);
                userDataRepository.save(userData);
            }
            else {
                System.out.println("username exists");
                //throw new UsernameAlreadyExistsException();
            }
        }
        else {
            System.out.println("email exists");
//            throw new UserEmailAlreadyExistsException();
        }
    }

    @Override
    public Optional<UserData> findByUsername(String username) {
        return userDataRepository.findByUsername(username);
    }

    @Override
    public List<UserData> getAllUsers() {
        return (List<UserData>) userDataRepository.findAll();
    }
}
