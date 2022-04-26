package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class Identifier implements ValueObject, Comparable<Identifier> {
    private String identifier;

    public Identifier(final String identifier) {
        if (StringPredicates.isNullOrEmpty(identifier)) {
            throw new IllegalArgumentException(
                    "Identifier should neither be null nor empty");
        }
        this.identifier = identifier;
    }

    protected Identifier() {
    }

    public static Identifier valueOf(final String identifier) {
        return new Identifier(identifier);
    }


    @Override
    public int compareTo(Identifier o) {
        return 0;
    }
}
