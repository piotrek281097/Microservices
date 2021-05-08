package pp.reservations.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pp.reservations.dto.BookUpdateStatusDto;


@Service
public class BooksProducer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProperties.Producer.class);
    private static final String TOPIC = "update-book-status";

    @Autowired
    private KafkaTemplate<String, BookUpdateStatusDto> kafkaTemplate;

    public void sendMessage(BookUpdateStatusDto message) {
        logger.info(String.format("#### -> Producing message -> %s", message));
        this.kafkaTemplate.send(TOPIC, message);
    }
}
