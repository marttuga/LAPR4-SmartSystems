package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class PriceTableID implements ValueObject, Comparable<PriceTableID> {
    private String priceTableID;

    public PriceTableID(final String identification) {
        if (StringPredicates.isNullOrEmpty(identification)) {
            throw new IllegalArgumentException(
                    "PriceTableID should neither be null nor empty");
        }
        this.priceTableID = identification;
    }

    protected PriceTableID() {
    }

    public static PriceTableID valueOf(final String identification) {
        return new PriceTableID(identification);
    }


    @Override
    public String toString() {
        return this.priceTableID;
    }


    @Override
    public int compareTo(PriceTableID o) {
        return 0;
    }
}
