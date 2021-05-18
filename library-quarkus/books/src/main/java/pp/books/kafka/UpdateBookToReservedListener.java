package pp.books.kafka;

import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;
import pp.books.dto.BookUpdateStatusDto;
import pp.books.repository.BookRepository;
import pp.books.service.BookService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UpdateBookToReservedListener {

    private final Logger logger = Logger.getLogger(UpdateBookToReservedListener.class);

    @Inject
    BookService bookService;

    @Inject
    BookRepository bookRepository;

    @Incoming("update-book-status-reserved-in")
    public void receiveBookReserved(Record<Long, String> record) {
        logger.infof("Got a reserved book: %d - %s", record.key(), record.value());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("RUN RUN res");
                bookService.updateBookStatus(new BookUpdateStatusDto(record.value(), "RESERVED", record.key()));
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
