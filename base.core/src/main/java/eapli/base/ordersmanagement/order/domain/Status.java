package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public enum Status implements ValueObject {

    REGISTERED,
    PAYMENT_PENDING,
    PAID,
    BEING_PREPARED,
    DISPATCHED,
    DELIVERED_BY_CARRIER,


}
