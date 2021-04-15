package books.kafka;

import books.dto.ReservationUpdateStatusDto;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface ReservationsClient {

    @Topic("update-reservation-status")
    void updateReservationStatus(@KafkaKey String key, ReservationUpdateStatusDto reservationUpdateStatusDto);
}
