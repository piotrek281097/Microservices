package reservations.kafka;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import reservations.dto.BookUpdateStatusDto;

@KafkaClient
public interface BooksClient {

    @Topic("update-book-status")
    void updateBookStatus(@KafkaKey String key, BookUpdateStatusDto bookUpdateStatusDto);
}
