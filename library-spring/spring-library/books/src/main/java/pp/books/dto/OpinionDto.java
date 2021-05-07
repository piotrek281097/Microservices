package pp.books.dto;


import lombok.Data;
import pp.books.domain.Book;

@Data
public class OpinionDto {

    private Long id;

    private int grade;

    private String review;

    private Book book;

}
