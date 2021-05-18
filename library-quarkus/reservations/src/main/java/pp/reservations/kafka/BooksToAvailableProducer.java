package pp.reservations.kafka;

import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import pp.reservations.dto.BookUpdateStatusDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class BooksToAvailableProducer {

    @Inject
    @Channel("update-book-status-available-out")
    Emitter<Record<Long, String>> emitterBookAvailable;

    public void updateBookAvailable(BookUpdateStatusDto book) {
        emitterBookAvailable.send(Record.of(book.getReservationId(), book.getBookIdentifier()));
    }
}
