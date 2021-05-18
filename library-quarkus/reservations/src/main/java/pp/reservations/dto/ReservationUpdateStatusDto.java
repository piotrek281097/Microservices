package pp.reservations.dto;

import java.io.Serializable;

public class ReservationUpdateStatusDto implements Serializable {

    private String newReservationStatus;

    private Long reservationId;

    public ReservationUpdateStatusDto() {
    }

    public ReservationUpdateStatusDto(Long reservationId, String newReservationStatus) {
        this.newReservationStatus = newReservationStatus;
        this.reservationId = reservationId;
    }

    public String getNewReservationStatus() {
        return newReservationStatus;
    }

    public void setNewReservationStatus(String newReservationStatus) {
        this.newReservationStatus = newReservationStatus;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }
}
