package pp.books.dto;


import lombok.Data;

@Data
public class BookUpdateStatusDto {

    private String bookIdentifier;

    private String newBookStatus;

    private Long reservationId;

}
