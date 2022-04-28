package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;


@Embeddable
public class PriceOrder  implements ValueObject {

    private double priceOrder;

    @Column(insertable = false, updatable = false)

    private ShippingCost shippingCost;

    public PriceOrder() {
    }

    public PriceOrder(double priceOrder, ShippingCost shippingCost) {
        this.priceOrder = priceOrder;
        this.shippingCost = shippingCost;
    }

    @Override
    public String toString() {
        return String.format("total price: %f\n", priceOrder);
    }
}
