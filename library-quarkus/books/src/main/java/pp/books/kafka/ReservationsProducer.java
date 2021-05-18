package pp.books.kafka;

import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import pp.books.dto.ReservationUpdateStatusDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ReservationsProducer {

    @Inject
    @Channel("update-reservation-status-out")
    Emitter<Record<Long, String>> emitter;

    public void updateReservationStatus(ReservationUpdateStatusDto reservationUpdateStatusDto) {
        System.out.println("Send message");
        emitter.send(Record.of(reservationUpdateStatusDto.getReservationId(), reservationUpdateStatusDto.getNewReservationStatus()));
    }

}
