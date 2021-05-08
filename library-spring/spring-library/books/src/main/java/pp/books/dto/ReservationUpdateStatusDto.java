package pp.books.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ReservationUpdateStatusDto implements Serializable {

    private String newReservationStatus;

    private Long reservationId;

}
