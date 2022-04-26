package eapli.base.ordersmanagement.customer.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Name implements ValueObject{


    private String firstName;


    private String lastName;

    private String fullName;
    public Name() {

    }

    public Name(String firstName, String lastName) {
        Preconditions.nonEmpty(firstName, "First Name cannot be null or empty");
        Preconditions.nonEmpty(lastName, "Last Name cannot be null or empty");
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Name(String fullName) {
        Preconditions.nonEmpty(fullName, "Name cannot be null or empty");
        this.fullName = fullName;
    }

}
