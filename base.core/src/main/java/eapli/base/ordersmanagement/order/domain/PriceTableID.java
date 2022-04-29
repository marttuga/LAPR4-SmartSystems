package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class PriceTableID implements ValueObject, Comparable<PriceTableID> {
    private String priceTID;

    public PriceTableID(final String identification) {
        if (StringPredicates.isNullOrEmpty(identification)) {
            throw new IllegalArgumentException(
                    "PriceTableID should neither be null nor empty");
        }
        this.priceTID = identification;
    }

    protected PriceTableID() {
    }

    public static PriceTableID valueOf(final String identification) {
        return new PriceTableID(identification);
    }


    @Override
    public String toString() {
        return this.priceTID;
    }


    @Override
    public int compareTo(PriceTableID o) {
        return 0;
    }
}
