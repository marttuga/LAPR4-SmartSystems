package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;


public enum Status implements ValueObject {

    REGISTERED,
    TO_BE_PREPARED,
    BEING_PREPARED,

    PREPARED,
    DISPATCHED,
    DELIVERED,


}
