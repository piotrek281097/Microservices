package pp.reservations.kafka;

import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import pp.reservations.dto.BookUpdateStatusDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class BooksToReservedProducer {

    @Inject
    @Channel("update-book-status-reserved-out")
    Emitter<Record<Long, String>> emitterBookReserved;

    public void updateBookReserved(BookUpdateStatusDto book) {
        emitterBookReserved.send(Record.of(book.getReservationId(), book.getBookIdentifier()));
    }

}
