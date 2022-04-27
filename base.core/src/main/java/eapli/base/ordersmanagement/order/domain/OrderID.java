package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class OrderID implements ValueObject,  Comparable<OrderID>{
    private String orderID;

    public OrderID(final String orderIdentification) {
        if (StringPredicates.isNullOrEmpty(orderIdentification)) {
            throw new IllegalArgumentException(
                    "OrderID should neither be null nor empty");
        }
        this.orderID = orderIdentification;
    }

    protected OrderID() {
    }

    public static OrderID valueOf(final String orderIdentification) {
        return new OrderID(orderIdentification);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderID orderID1 = (OrderID) o;
        return Objects.equals(orderID, orderID1.orderID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID);
    }

    @Override
    public String toString() {
        return this.orderID;
    }

    @Override
    public int compareTo(OrderID o) {
        return 0;
    }
}
