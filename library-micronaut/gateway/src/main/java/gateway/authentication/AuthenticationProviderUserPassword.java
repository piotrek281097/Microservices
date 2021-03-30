package gateway.authentication;

import gateway.domain.UserData;
import gateway.repository.UserDataRepository;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.*;
import io.reactivex.Maybe;
import org.reactivestreams.Publisher;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider {

    protected final UserDataRepository userDataRepository;

    protected final PasswordEncoder passwordEncoder;

    protected final UsersStore usersStore;

    public AuthenticationProviderUserPassword(UserDataRepository userDataRepository, PasswordEncoder passwordEncoder, UsersStore usersStore) {
        this.userDataRepository = userDataRepository;
        this.passwordEncoder = passwordEncoder;
        this.usersStore = usersStore;
    }

    @Override
    public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        String username = authenticationRequest.getIdentity().toString();
        Optional<UserData> foundUser = userDataRepository.findByUsername(username);
        return Maybe.<AuthenticationResponse>create(emitter -> {
            if (foundUser.isEmpty()) {
                if (!usersStore.users.get(username).isEmpty() && usersStore.users.get(username).equals(authenticationRequest.getSecret().toString())) {
                    System.out.println("ADMIN");
                    emitter.onSuccess(new UserDetails(username, Collections.singletonList(usersStore.roles.get(username))));
                } else {
                    emitter.onError(new AuthenticationException(new AuthenticationFailed()));
                }
            }
            else if (passwordEncoder.matches(authenticationRequest.getSecret().toString(), foundUser.get().getPassword())) {
                emitter.onSuccess(new UserDetails(foundUser.get().getUsername(), Collections.singletonList("USER")));
            } else {
                emitter.onError(new AuthenticationException(new AuthenticationFailed()));
            }
        }).toFlowable();
    }
}
