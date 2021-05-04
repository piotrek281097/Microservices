package pp.books.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "opinions")
@Data
@NoArgsConstructor
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int grade;

    private String review;

    @ManyToOne
    @JsonIgnore
    private Book book;
}
