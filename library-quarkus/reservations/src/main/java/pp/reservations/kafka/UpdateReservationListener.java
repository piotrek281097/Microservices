package pp.reservations.kafka;

import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;
import pp.reservations.dto.ReservationUpdateStatusDto;
import pp.reservations.service.ReservationService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UpdateReservationListener {

    private final Logger logger = Logger.getLogger(UpdateReservationListener.class);

    @Inject
    ReservationService reservationService;

    @Incoming("update-reservation-status-in")
    public void receiveBookReserved(Record<Long, String> record) {
        logger.infof("Got a reservation change status: %d - %s", record.key(), record.value());

        Runnable runnable = () -> reservationService.updateReservationStatus(new ReservationUpdateStatusDto(record.key(), record.value()));

        Thread thread = new Thread(runnable);
        thread.start();
    }

}
