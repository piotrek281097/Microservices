package reservations.kafka;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import reservations.dto.ReservationUpdateStatusDto;
import reservations.service.ReservationService;

import javax.inject.Inject;

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class UpdateReservationStatusListener {

    @Inject
    protected ReservationService reservationService;

    public UpdateReservationStatusListener(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @Topic("update-reservation-status")
    public void receive(@KafkaKey String key, ReservationUpdateStatusDto reservationUpdateStatusDto) {
        reservationService.updateReservationStatus(reservationUpdateStatusDto);
        System.out.println("Reservation status update");
    }
}
