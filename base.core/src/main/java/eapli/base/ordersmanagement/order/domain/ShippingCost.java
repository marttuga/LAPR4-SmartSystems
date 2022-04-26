package eapli.base.ordersmanagement.order.domain;

import eapli.base.ordersmanagement.product.domain.Weight;
import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class ShippingCost implements ValueObject {

    private double shippingCost;

    @Embedded
    @Enumerated(EnumType.STRING)
    private SalesRegion salesRegion;

    @Embedded
    private Weight weight;

    public ShippingCost() {
    }

    public ShippingCost(double shippingCost, SalesRegion salesRegion, Weight weight) {
        this.shippingCost = shippingCost;
        this.salesRegion = salesRegion;
        this.weight = weight;
    }
}
