package pp.books.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class BookIdentifierAlreadyExists extends RuntimeException {

    public BookIdentifierAlreadyExists(String message) {
        super(message);
    }
}
