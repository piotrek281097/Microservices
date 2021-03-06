package pp.users.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserData {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String password;

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    private String telephone;

    @NotBlank
    private String pesel;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public UserData(@NotBlank String password, @NotBlank String username) {
        this.password = password;
        this.username = username;
    }
}
