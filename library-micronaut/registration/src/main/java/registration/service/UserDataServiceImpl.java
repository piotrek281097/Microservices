package registration.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import registration.domain.Address;
import registration.domain.UserData;
import registration.exception.UserEmailAlreadyExistsException;
import registration.exception.UsernameAlreadyExistsException;
import registration.repository.UserDataRepository;

import javax.inject.Singleton;
import java.util.ArrayList;
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
            } else {
                throw new UsernameAlreadyExistsException();
            }
        } else {
            throw new UserEmailAlreadyExistsException();
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
        userDataRepository.update(userData);
    }

    @Override
    public String saveTestData() {
        List<UserData> users = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            UserData userData = new UserData();
            userData.setUsername("username" + i);
            userData.setPassword(passwordEncoder.encode("password" + i));
            userData.setEmail("email" + i);
            userData.setName("name" + i);
            userData.setSurname("surname" + i);
            userData.setPesel("11111111111");
            userData.setTelephone("12345678" + i);
            Address address = new Address();
            address.setStreet("street");
            address.setPostalCode("12-234");
            address.setCity("city" + i);
            address.setApartmentNumber("" + i);
            userData.setAddress(address);
            users.add(userData);
        }

        long startTime = System.currentTimeMillis();
        for (UserData user : users) {
            userDataRepository.save(user);
        }
        long duration = System.currentTimeMillis() - startTime;
        return "saving users finished - " + duration + " ms";
    }
}
