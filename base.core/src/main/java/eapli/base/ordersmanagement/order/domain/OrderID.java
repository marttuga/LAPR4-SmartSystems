package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class OrderID implements ValueObject, Comparable<OrderID> {
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
    public int hashCode() {
        return this.orderID.hashCode();
    }

    @Override
    public String toString() {
        return this.orderID;
    }

    @Override
    public int compareTo(final OrderID o) {
        return orderID.compareTo(o.orderID);
    }
}
