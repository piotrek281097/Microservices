package books.configuration;

import books.kafka.ReservationsClient;
import books.repository.BookRepository;
import books.repository.OpinionRepository;
import books.service.BookService;
import books.service.BookServiceImpl;
import io.micronaut.context.annotation.Bean;

import javax.inject.Singleton;

public class Configuration {

    @Bean
    @Singleton
    BookService bookService( ReservationsClient reservationsClient, BookRepository bookRepository, OpinionRepository opinionRepository) {
        return new BookServiceImpl(reservationsClient, bookRepository, opinionRepository);
    }

}
