package pp.users.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address extends PanacheEntity {

    private String city;

    private String postalCode;

    private String street;

    private String apartmentNumber;

    public Address() {
    }

    public Address(String city, String postalCode, String street, String apartmentNumber) {
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.apartmentNumber = apartmentNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }
}
