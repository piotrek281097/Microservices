package pp.books.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationUpdateStatusDto {

    private String newReservationStatus;

    private Long reservationId;

}
