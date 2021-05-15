package pp.gateway.security;


public class ResponseEntity {

    public ResponseEntity(){}

    public ResponseEntity(boolean isValid, String message) {
        this.isValid = isValid;
        this.message = message;
    }

    private boolean isValid;
    private String message;

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
