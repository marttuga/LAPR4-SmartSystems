package eapli.base.ordersmanagement.customer.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public enum CustomerGender implements ValueObject {
    MALE,
    FEMALE,
    OTHER
}
