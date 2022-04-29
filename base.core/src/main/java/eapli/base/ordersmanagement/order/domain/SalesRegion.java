package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;

@Embeddable
public enum SalesRegion  implements ValueObject {

    PORTUGAL,
    SPAIN,
    FRANCE,
    GERMANY,
    ITALY,
LUXEMBOURG,
    MALTA,
    FINLAND,
    AUSTRIA,


}
