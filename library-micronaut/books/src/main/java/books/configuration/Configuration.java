package books.configuration;

import books.repository.BookRepository;
import books.service.BookService;
import books.service.BookServiceImpl;
import io.micronaut.context.annotation.Bean;

import javax.inject.Singleton;

public class Configuration {

    @Bean
    @Singleton
    BookService bookService(BookRepository bookRepository) {
        return new BookServiceImpl(bookRepository);
    }

}
