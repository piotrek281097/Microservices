package pp.books.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pp.books.dto.ReservationUpdateStatusDto;


@Service
public class ReservationsProducer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProperties.Producer.class);
    private static final String TOPIC = "update-reservation-status";

    @Autowired
    private KafkaTemplate<String, ReservationUpdateStatusDto> kafkaTemplate;

    public void sendMessage(ReservationUpdateStatusDto message) {
        logger.info(String.format("#### -> Producing message -> %s", message));
        this.kafkaTemplate.send(TOPIC, message);
    }
}
