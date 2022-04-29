package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

import java.util.Objects;
@Embeddable
public class LineOrderID implements ValueObject,  Comparable<LineOrderID>{

    private String lorderIden;

    public LineOrderID(final String lorderIdentification) {
        if (StringPredicates.isNullOrEmpty(lorderIdentification)) {
            throw new IllegalArgumentException(
                    "Line OrderID should neither be null nor empty");
        }
        this.lorderIden = lorderIdentification;
    }


    protected LineOrderID() {
    }

    public static LineOrderID valueOf(final String lorderIdentification) {
        return new LineOrderID(lorderIdentification);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineOrderID lorderID1 = (LineOrderID) o;
        return Objects.equals(lorderIden, lorderID1.lorderIden);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lorderIden);
    }

    @Override
    public String toString() {
        return this.lorderIden;
    }

    @Override
    public int compareTo(LineOrderID o) {
        return 0;
    }
}

