package pp.books.dto;


import lombok.Data;
import pp.books.domain.Book;

import java.io.Serializable;

@Data
public class OpinionDto implements Serializable {

    private Long id;

    private int grade;

    private String review;

    private Book book;

}
