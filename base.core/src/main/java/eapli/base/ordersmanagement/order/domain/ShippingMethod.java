package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

public enum ShippingMethod implements ValueObject {

    Standart,
    Blue,
    Green,

}


