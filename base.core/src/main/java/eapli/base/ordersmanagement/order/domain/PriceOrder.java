package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Money;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;


@Embeddable
public class PriceOrder  implements ValueObject {

    private Money priceOrder;

    @Column(insertable = false, updatable = false)

    private ShippingCost shippingCost;

    public PriceOrder() {
    }

    public PriceOrder(Money priceOrder, ShippingCost shippingCost) {
        this.priceOrder = priceOrder;
        this.shippingCost = shippingCost;
    }

    @Override
    public String toString() {
        return "total price: \n"+ priceOrder;
    }
}
