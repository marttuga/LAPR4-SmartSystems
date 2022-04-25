package eapli.base.ordersmanagement.product.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class UniqueInternalCode implements ValueObject, Comparable<UniqueInternalCode> {
    private String uniqueInternalCode;

    public UniqueInternalCode(final String internalCode) {
        if (StringPredicates.isNullOrEmpty(internalCode)) {
            throw new IllegalArgumentException(
                    "UniqueInternalCode should neither be null nor empty");
        }
        this.uniqueInternalCode = internalCode;
    }

    protected UniqueInternalCode() {
    }

    public static UniqueInternalCode valueOf(final String internalCode) {
        return new UniqueInternalCode(internalCode);
    }

    @Override
    public int hashCode() {
        return this.uniqueInternalCode.hashCode();
    }

    @Override
    public String toString() {
        return this.uniqueInternalCode;
    }


    @Override
    public int compareTo(final UniqueInternalCode o) {
        return uniqueInternalCode.compareTo(o.uniqueInternalCode);
    }
}

