package pp.users.service;

import pp.users.domain.UserData;

import java.util.List;
import java.util.Optional;

public interface UserDataService {

    void register(UserData userData);

    UserData findByUsername(String email);

    List<UserData> getAllUsers();

    void deleteUserById(long userId);

    UserData getUserById(long userId);

    UserData getUserByUsername(String username);

    void updateUser(UserData userData);
}
