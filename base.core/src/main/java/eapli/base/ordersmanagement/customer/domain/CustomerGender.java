package eapli.base.ordersmanagement.customer.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

public enum CustomerGender implements ValueObject {
    MALE,
    FEMALE,
    OTHER
}
