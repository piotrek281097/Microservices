package gateway.exception;

public class BooksException extends RuntimeException {

    private final String message;

    public BooksException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
