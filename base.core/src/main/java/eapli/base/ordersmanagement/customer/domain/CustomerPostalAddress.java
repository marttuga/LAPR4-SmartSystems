package eapli.base.ordersmanagement.customer.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

public class CustomerPostalAddress implements ValueObject {
    private String street;
    private int doorNumber;
    private String city;
    private String country;
    private String postalCode;
    private String postalAddress;

    public CustomerPostalAddress(){
    }

    public CustomerPostalAddress(String street, int doorNumber, String city, String country, String postalCode){
        Preconditions.nonEmpty(street, "Street cannot be null or empty");
        Preconditions.nonEmpty(String.valueOf(doorNumber), "Door Number cannot be null or empty");
        Preconditions.nonEmpty(city, "City cannot be null or empty");
        Preconditions.nonEmpty(country, "Country Name cannot be null or empty");
        this.street = street;
        this.doorNumber = doorNumber;
        this.city = city;
        this.country = country;
        this.postalCode=postalCode;
    }

    public CustomerPostalAddress(String postalAddress){
        this.postalAddress = postalAddress;
    }
}
