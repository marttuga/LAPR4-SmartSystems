package eapli.base.ordersmanagement.order.domain;

import eapli.base.ordersmanagement.product.domain.Weight;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Money;

import javax.persistence.*;

@Embeddable
public class ShippingCost implements ValueObject {

    @Column (insertable = false, updatable = false)
    private Money shippingCost;


    @Enumerated(EnumType.STRING)
    private SalesRegion salesRegion;

    @Column(insertable = false, updatable = false)

    private Weight weight;

    public ShippingCost() {
    }

    public ShippingCost(Money shippingCost, SalesRegion salesRegion, Weight weight) {
        this.shippingCost = shippingCost;
        this.salesRegion = salesRegion;
        this.weight = weight;
    }
}
