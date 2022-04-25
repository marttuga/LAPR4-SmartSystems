package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class ShippingCost implements ValueObject {

    private int volume;

    @Embedded
    private SalesRegion salesRegion;
}
