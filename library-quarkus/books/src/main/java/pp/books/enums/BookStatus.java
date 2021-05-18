package pp.books.enums;

public enum BookStatus {
    AVAILABLE("AVAILABLE"),
    RESERVED("RESERVED");

    public final String status;

    BookStatus(String status) {
        this.status = status;
    }

    void from(String status) {

    }
}
