package pp.users.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pp.users.domain.UserData;
import pp.users.domain.UserDetailsImpl;
import pp.users.repository.UserDataRepository;

import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private static final String ADMIN_USERNAME = "admin";
    private UserDataRepository userDataRepository;

    @Autowired
    public JwtUserDetailsService(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (ADMIN_USERNAME.equals(username)) {
            UserData adminData = new UserData(
                    "$2a$10$PH0p2x2x8oi5bKx.80Bt7ubMAiHdZnqm9TC/Cpss9VoccyTYw1AoC", //nimda
                    "admin"
            );
            return UserDetailsImpl.build(adminData, "ROLE_ADMIN");
        } else {
            Optional<UserData> user = userDataRepository.findByUsername(username);
            user.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found!"));
            return UserDetailsImpl.build(user.get(), "ROLE_USER");
        }
    }
}
