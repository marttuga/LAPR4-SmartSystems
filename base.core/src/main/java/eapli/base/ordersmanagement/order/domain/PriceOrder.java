package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class PriceOrder  implements ValueObject {

    private double priceOrder;

    @Embedded
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
