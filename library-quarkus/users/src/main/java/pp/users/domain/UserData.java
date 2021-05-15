package pp.users.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Table(name = "users")
@Entity
public class UserData extends PanacheEntity {

    @NotBlank
    public String username;

    @NotBlank
    public String password;

    @NotBlank
    public String email;

    @NotBlank
    public String name;

    @NotBlank //zmien na notNull?
    public String surname;

    @NotBlank
    public String telephone;

    @NotBlank
    public String pesel;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    public Address address;

    public UserData() {
    }

    public UserData(@NotBlank String username, @NotBlank String password, @NotBlank String email, @NotBlank String name, @NotBlank String surname, @NotBlank String telephone, @NotBlank String pesel, Address address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.pesel = pesel;
        this.address = address;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
