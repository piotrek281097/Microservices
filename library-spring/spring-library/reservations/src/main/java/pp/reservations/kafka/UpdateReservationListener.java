package pp.reservations.kafka;

import org.apache.kafka.clients.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import pp.reservations.dto.ReservationUpdateStatusDto;
import pp.reservations.service.ReservationService;

import java.io.IOException;

@Service
public class UpdateReservationListener {

    private final Logger logger = LoggerFactory.getLogger(Producer.class);

    private ReservationService reservationService;

    public UpdateReservationListener(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @KafkaListener(topics = "update-reservation-status", groupId = "group_id")
    public void consume(ReservationUpdateStatusDto message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
        reservationService.updateReservationStatus(message);
    }
}
