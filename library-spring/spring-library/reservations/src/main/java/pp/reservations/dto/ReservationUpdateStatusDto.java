package pp.reservations.dto;


public class ReservationUpdateStatusDto {

    private String newReservationStatus;

    private Long reservationId;

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
