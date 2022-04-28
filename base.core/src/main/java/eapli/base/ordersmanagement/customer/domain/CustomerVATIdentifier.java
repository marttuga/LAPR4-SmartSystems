package eapli.base.ordersmanagement.customer.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
public class CustomerVATIdentifier implements ValueObject {
    private String vatIdentifier;

    public CustomerVATIdentifier(){
    }

    public CustomerVATIdentifier(String vatIdentifier){
        Preconditions.nonEmpty(vatIdentifier, "VAT identifier cannot be null or empty");

        Pattern vatIdRegex = Pattern.compile("^\\d{7}$");
        Matcher matcher = vatIdRegex.matcher(vatIdentifier);

        if (!matcher.find()) {
            throw new IllegalArgumentException("VAT identifier invalid format");
        }

        this.vatIdentifier=vatIdentifier;
    }
}
