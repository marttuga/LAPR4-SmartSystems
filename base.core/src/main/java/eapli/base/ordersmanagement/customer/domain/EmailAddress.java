package eapli.base.ordersmanagement.customer.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class EmailAddress implements ValueObject {
    @Embedded
    private String emailAddress;

    public EmailAddress(){

    }

    public EmailAddress(String emailAddress){
        Preconditions.nonEmpty(emailAddress, "The email address should neither be null nor empty");
        Preconditions.ensure(StringPredicates.isEmail(emailAddress), "Invalid email format");
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return emailAddress;
    }
}
