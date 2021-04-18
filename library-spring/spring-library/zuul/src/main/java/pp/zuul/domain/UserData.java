package pp.zuul.domain;


import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Builder
@Data
@Entity
@Table(name = "users")
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
    private String name; // chyba mozna pousuwac pola poza username i password potrzebne do logowania

    @NotBlank
    private String surname;

    @NotBlank
    private String telephone;

    @NotBlank
    private String pesel;

    public UserData(@NotBlank String password, @NotBlank String username, @NotBlank String email, @NotBlank String name, @NotBlank String surname, @NotBlank String telephone, @NotBlank String pesel) {
        this.password = password;
        this.username = username;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.pesel = pesel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
}
