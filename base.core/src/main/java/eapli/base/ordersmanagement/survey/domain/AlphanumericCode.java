package eapli.base.ordersmanagement.survey.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class AlphanumericCode implements ValueObject, Comparable<AlphanumericCode> {
    private String alphanumericCode;


    
    public AlphanumericCode(final String alphaNumericCode) {
        if (StringPredicates.isNullOrEmpty(alphaNumericCode)) {
            throw new IllegalArgumentException(
                    "Survey alpha numeric code should neither be null nor empty");
        }
        this.alphanumericCode = alphaNumericCode;
    }

    protected AlphanumericCode() {
    }

    public static AlphanumericCode valueOf(final String alphanumericCode) {
        return new AlphanumericCode(alphanumericCode);
    }

    @Override
    public int hashCode() {
        return this.alphanumericCode.hashCode();
    }

    @Override
    public String toString() {
        return this.alphanumericCode;
    }

    @Override
    public int compareTo(final AlphanumericCode o) {
        return alphanumericCode.compareTo(o.alphanumericCode);
    }
}
