package pp.zuul.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pp.zuul.auth.UserDetailsImpl;
import pp.zuul.domain.UserData;
import pp.zuul.repository.UserDataRepository;

import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    UserDataRepository userDataRepository;

    @Autowired
    public JwtUserDetailsService(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserData> user = userDataRepository.findByUsername(username);
//        System.out.println(user.get().getRoles().toArray().length);
        user.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found!"));
        return UserDetailsImpl.build(user.get());
    }
}
