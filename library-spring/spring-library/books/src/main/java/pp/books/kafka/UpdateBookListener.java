package pp.books.kafka;

import org.apache.kafka.clients.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import pp.books.dto.BookUpdateStatusDto;
import pp.books.service.BookService;

import java.io.IOException;

@Service
public class UpdateBookListener {

    private final Logger logger = LoggerFactory.getLogger(Producer.class);

    private BookService bookService;

    public UpdateBookListener(BookService bookService) {
        this.bookService = bookService;
    }

    @KafkaListener(topics = "update-book-status", groupId = "group_id")
    public void consume(BookUpdateStatusDto message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
        bookService.updateBookStatus(message);
    }
}
