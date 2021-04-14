package gateway.exception;

public class RegisterException extends RuntimeException {

    private final String message;

    public RegisterException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
