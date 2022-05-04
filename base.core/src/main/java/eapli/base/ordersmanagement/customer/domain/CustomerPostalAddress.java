package eapli.base.ordersmanagement.customer.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

public class CustomerPostalAddress implements ValueObject, Comparable<CustomerPostalAddress>{
    private String street;
    private int doorNumber;
    private String city;
    private String country;
    private int postalCode;


    protected CustomerPostalAddress(){
    }

    public CustomerPostalAddress(String street, int doorNumber, String city, String country, int postalCode){
        Preconditions.nonEmpty(street, "Street cannot be null or empty");
        Preconditions.nonEmpty(String.valueOf(doorNumber), "Door Number cannot be null or empty");
        Preconditions.nonEmpty(city, "City cannot be null or empty");
        Preconditions.nonEmpty(country, "Country Name cannot be null or empty");
        Preconditions.nonEmpty(String.valueOf(postalCode), "Country Name cannot be null or empty");
        this.street = street;
        this.doorNumber = doorNumber;
        this.city = city;
        this.country = country;
        this.postalCode=postalCode;
    }

    public static CustomerPostalAddress valueOf(final String street, final int doorNumber, final String city, final String country, final int postalCode){
        return new CustomerPostalAddress(street, doorNumber, city, country, postalCode);
    }

    @Override
    public String toString() {
        return "CustomerPostalAddress: " +
                "street='" + street + '\'' +
                ", doorNumber=" + doorNumber +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", postalCode=" + postalCode ;
    }

    @Override
    public int compareTo(CustomerPostalAddress o) {
        return 0;
    }
}
