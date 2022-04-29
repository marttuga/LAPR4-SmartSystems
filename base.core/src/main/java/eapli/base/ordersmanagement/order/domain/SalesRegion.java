package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;

@Embeddable
public enum SalesRegion  implements ValueObject {
              // shippingcost
    PORTUGAL, // 0,2
    SPAIN,    // 0,2
    FRANCE,
    GERMANY,
    ITALY,
    MALTA,
    FINLAND,
    AUSTRIA,

}
