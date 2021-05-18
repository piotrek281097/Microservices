package pp.books.kafka;

import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;
import pp.books.dto.BookUpdateStatusDto;
import pp.books.service.BookService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UpdateBookToAvailableListener {

    private final Logger logger = Logger.getLogger(UpdateBookToReservedListener.class);

    @Inject
    BookService bookService;

    @Incoming("update-book-status-available-in")
    public void receiveBookAvailable(Record<Long, String> record) {
        logger.infof("Got a available book: %d - %s", record.key(), record.value());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                bookService.updateBookStatus(new BookUpdateStatusDto(record.value(), "AVAILABLE", record.key()));
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
