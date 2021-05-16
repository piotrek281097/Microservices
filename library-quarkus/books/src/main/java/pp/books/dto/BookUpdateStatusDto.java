package pp.books.dto;

import java.io.Serializable;

public class BookUpdateStatusDto implements Serializable {

    private String bookIdentifier;

    private String newBookStatus;

    private Long reservationId;

    public BookUpdateStatusDto() {
    }

    public String getBookIdentifier() {
        return bookIdentifier;
    }

    public void setBookIdentifier(String bookIdentifier) {
        this.bookIdentifier = bookIdentifier;
    }

    public String getNewBookStatus() {
        return newBookStatus;
    }

    public void setNewBookStatus(String newBookStatus) {
        this.newBookStatus = newBookStatus;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }
}
