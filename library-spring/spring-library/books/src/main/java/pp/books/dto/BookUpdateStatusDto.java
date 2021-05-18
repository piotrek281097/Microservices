package pp.books.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookUpdateStatusDto implements Serializable {

    private String bookIdentifier;

    private String newBookStatus;

    private Long reservationId;

}
