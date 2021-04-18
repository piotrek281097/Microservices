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

    private UserDataRepository userDataRepository;

    @Autowired
    public JwtUserDetailsService(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserData> user = userDataRepository.findByUsername(username);
        return UserDetailsImpl.build(user.get());
    }
}
