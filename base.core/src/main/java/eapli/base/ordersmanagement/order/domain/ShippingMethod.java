package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class ShippingMethod implements ValueObject {

    private static enum Type {
        Standart,
        Blue,
        Green,
    }

    private Type type;
    @Embedded
    private ShippingCost shippingCost;

    public ShippingMethod() {
    }

    public ShippingMethod(Type type, ShippingCost shippingCost) {
        this.type = type;
        this.shippingCost = shippingCost;
    }
}


