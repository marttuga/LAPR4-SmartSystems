package eapli.base.ordersmanagement.customer.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;


import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
public class CustomerPhoneNumber implements ValueObject , Comparable<CustomerPhoneNumber>{
    @Embedded
    private String phoneNumber;

    public CustomerPhoneNumber() {
    }

    public CustomerPhoneNumber(String phoneNumber) {

        if (StringPredicates.isNullOrEmpty(phoneNumber)) {
            throw new IllegalArgumentException("Phone Number should neither be null nor empty");
        }
        if (phoneNumber.matches("\\d{9}")) {
            this.phoneNumber = phoneNumber;
        }
    }

    public static CustomerPhoneNumber valueOf(final String phoneNumber) {
        return new CustomerPhoneNumber(phoneNumber);
    }

    @Override
    public int compareTo(CustomerPhoneNumber o) {
        return 0;
    }
}
