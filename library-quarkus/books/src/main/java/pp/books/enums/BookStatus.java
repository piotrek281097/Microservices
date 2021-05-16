package pp.books.enums;

public enum BookStatus {
    AVAILABLE("AVAILABLE"),
    RESERVED("RESERVED");

    public final String status;

    private BookStatus(String status) {
        this.status = status;
    }

    void from(String status) {

    }
}
