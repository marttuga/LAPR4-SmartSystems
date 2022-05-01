package eapli.base.ordersmanagement.customer.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerEmailAdress implements ValueObject, Comparable<CustomerEmailAdress> {

    private String email;

    @Override
    public String toString() {
        return   email ;
    }

    public CustomerEmailAdress() {
    }

    public CustomerEmailAdress(String email) {
        if (StringPredicates.isNullOrEmpty(email)) {
            throw new IllegalArgumentException("Email should neither be null nor empty");
        }

        if (!StringPredicates.isEmail(email)) {
            throw new IllegalArgumentException("Email Format invalid");
        }


        this.email = email;
    }

    public static CustomerEmailAdress valueOf(final String email){
        return new CustomerEmailAdress(email);
    }

    @Override
    public int compareTo(CustomerEmailAdress o) {
        return 0;
    }
}
