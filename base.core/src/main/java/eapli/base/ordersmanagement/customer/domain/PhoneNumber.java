package eapli.base.ordersmanagement.customer.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;


import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
public class PhoneNumber implements ValueObject {
    @Embedded
    private String phoneNumber;

    public PhoneNumber() {
    }

    public PhoneNumber(String phoneNumber) {

        if (StringPredicates.isNullOrEmpty(phoneNumber)) {
            throw new IllegalArgumentException("Phone Number should neither be null nor empty");
        }

        Pattern phoneNumberRegex = Pattern.compile("^\\d{9}$");
        Matcher matcher = phoneNumberRegex.matcher(phoneNumber);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Phone Number invalid format");
        }

        this.phoneNumber = phoneNumber;
    }
}
