package pp.users.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pp.users.domain.Address;
import pp.users.domain.UserData;
import pp.users.exception.EmailAlreadyExistsException;
import pp.users.exception.UsernameAlreadyExistsException;
import pp.users.repository.UserDataRepository;

import java.util.*;

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
        Optional<UserData> userFoundByEmail = userDataRepository.findByEmail(userData.getEmail());
        if (userFoundByEmail.isEmpty()) {
            Optional<UserData> userFoundUsername = userDataRepository.findByUsername(userData.getUsername());
            if (userFoundUsername.isEmpty()) {
                final String encodedPassword = encoder.encode(userData.getPassword());
                userData.setPassword(encodedPassword);
                userDataRepository.save(userData);
            }
            else {
                throw new UsernameAlreadyExistsException("Username already exists");
            }
        }
        else {
            throw new EmailAlreadyExistsException("Email already exists");
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

    @Override
    public String saveTestData() {
        List<UserData> users = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            users.add(
                    UserData.builder()
                            .username("username" + i)
                            .password(encoder.encode("password" + i))
                            .email("emial" + i + "@gmail.com")
                            .name("name" + i)
                            .surname("surname" + i)
                            .telephone("12365424" + i)
                            .pesel("11111111111")
                            .address(Address.builder()
                                    .street("street")
                                    .postalCode("12-345")
                                    .city("city" + i)
                                    .apartmentNumber("" + i)
                                    .build())
                            .build()
            );
        }

        long startTime = System.currentTimeMillis();
        for (UserData user : users) {
            userDataRepository.save(user);
        }
        long duration = System.currentTimeMillis() - startTime;
        return "saving users finished - " + duration + " ms";
    }
}
