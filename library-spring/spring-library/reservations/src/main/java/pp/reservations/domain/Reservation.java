package pp.reservations.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import pp.reservations.enums.ReservationStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String reservationIdentifier;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @NotNull
    private String bookTitle;

    @NotNull
    private String bookIdentifier;

    @NotNull
    private String borrowerUsername;

    @NotNull
    private String ownerUsername;

//    @ManyToOne
//    private Book book;
//
//    @ManyToOne
//    @JsonIgnore
//    private Reader reader;

    @Version
    private int optLock;

}
