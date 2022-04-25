package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class ShippingCost implements ValueObject {

    private int shippingCost;

    @Embedded
    @Enumerated(EnumType.STRING)
    private SalesRegion salesRegion;

/*    @Embedded
    private Weight weight;

    @Embedded
    private Volume volume;

    public ShippingCost() {
    }

    public ShippingCost(int shippingCost, SalesRegion salesRegion, Weight weight, Volume volume) {
        this.shippingCost = shippingCost;
        this.salesRegion = salesRegion;
        this.weight = weight;
        this.volume = volume;
    }*/
}
