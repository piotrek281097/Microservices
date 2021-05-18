package pp.books.dto;

import pp.books.domain.Book;
import java.io.Serializable;

public class OpinionDto implements Serializable {

    private Long id;

    private int grade;

    private String review;

    private Book book;

    public OpinionDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
