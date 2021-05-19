package books.kafka;

import books.dto.BookUpdateStatusDto;
import books.service.BookService;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;

import javax.inject.Inject;


@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class UpdateBookListener {

    @Inject
    protected BookService bookService;

    public UpdateBookListener(BookService bookService) {
        this.bookService = bookService;
    }

    @Topic("update-book-status")
    public void receive(@KafkaKey String key, BookUpdateStatusDto bookUpdateStatusDto) {
        bookService.updateBookStatus(bookUpdateStatusDto);
    }
}
