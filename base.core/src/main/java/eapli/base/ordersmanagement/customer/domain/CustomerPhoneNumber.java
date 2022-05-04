package eapli.base.ordersmanagement.customer.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;


import javax.persistence.Embeddable;

@Embeddable
public class CustomerPhoneNumber implements ValueObject , Comparable<CustomerPhoneNumber>{

    private String phoneNumber;

    protected CustomerPhoneNumber() {
    }

    public CustomerPhoneNumber(String phoneNumber) {

        if (StringPredicates.isNullOrEmpty(phoneNumber) || phoneNumber.length() != 9) {
            throw new IllegalArgumentException("Phone Number should neither be null nor empty");
        }
        this.phoneNumber=phoneNumber;
    }

    public static CustomerPhoneNumber valueOf(final String phoneNumber) {
        return new CustomerPhoneNumber(phoneNumber);
    }

    @Override
    public String toString() {
        return "CustomerPhoneNumber: " +
                "phoneNumber='" + phoneNumber ;
    }

    @Override
    public int compareTo(CustomerPhoneNumber o) {
        return 0;
    }
}
