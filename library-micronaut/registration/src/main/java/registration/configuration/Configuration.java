package registration.configuration;

import io.micronaut.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import registration.repository.UserDataRepository;
import registration.service.UserDataService;
import registration.service.UserDataServiceImpl;

import javax.inject.Singleton;

public class Configuration {

    @Bean
    @Singleton
    UserDataService userDataService(UserDataRepository userDataRepository, PasswordEncoder passwordEncoder) {
        return new UserDataServiceImpl(userDataRepository, passwordEncoder);
    }

    @Bean
    @Singleton
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoderService();
    }

}
