package gateway.configuration;

import gateway.authentication.UsersStore;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Singleton;

@Factory
public class Configuration {

    @Bean
    @Singleton
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoderService();
    }

}
