package reservations.configuration;

import io.micronaut.context.annotation.Bean;
import reservations.kafka.BooksClient;
import reservations.repository.ReservationRepository;
import reservations.service.ReservationService;
import reservations.service.ReservationServiceImpl;

import javax.inject.Singleton;

public class Configuration {

    @Bean
    @Singleton
    ReservationService reservationService(ReservationRepository reservationRepository, BooksClient booksClient) {
        return new ReservationServiceImpl(reservationRepository, booksClient);
    }

}
