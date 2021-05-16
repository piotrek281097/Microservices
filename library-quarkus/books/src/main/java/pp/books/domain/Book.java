package pp.books.domain;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import pp.books.enums.BookKind;
import pp.books.enums.BookStatus;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "books")
public class Book extends PanacheEntity {

    @NotNull
    private String title;

    private String author;

    @NotNull
    private String identifier;

    @Enumerated(EnumType.STRING)
    private BookKind bookKind;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @JsonbDateFormat(value = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @NotNull
    private String ownerUsername;

    private double avgRate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    private Set<Opinion> opinions = new HashSet<>();

    @Version
    private int optLock;

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public BookKind getBookKind() {
        return bookKind;
    }

    public void setBookKind(BookKind bookKind) {
        this.bookKind = bookKind;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public double getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(double avgRate) {
        this.avgRate = avgRate;
    }

    public Set<Opinion> getOpinions() {
        return opinions;
    }

    public void setOpinions(Set<Opinion> opinions) {
        this.opinions = opinions;
    }

    public int getOptLock() {
        return optLock;
    }

    public void setOptLock(int optLock) {
        this.optLock = optLock;
    }
}
