package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class PriceTableID implements ValueObject {
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
    public int hashCode() {
        return this.priceTableID.hashCode();
    }

    @Override
    public String toString() {
        return this.priceTableID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceTableID that = (PriceTableID) o;
        return Objects.equals(priceTableID, that.priceTableID);
    }
}
