package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.*;

@Embeddable
public class ShippingMethod implements ValueObject {

    public static enum Type {
        Standart,
        Blue,
        Green,
    }

    private Type type;


    private ShippingCost ship;

    protected ShippingMethod() {
    }

    public Type TypeOfDelivery() {
        return type;
    }

    public ShippingMethod(Type type, ShippingCost ship) {
        this.type = type;
        this.ship = ship;
    }

    public ShippingMethod(ShippingCost ship) {
        this.ship = ship;
    }

    public ShippingMethod(Type type) {
        this.type = type;
    }
}


