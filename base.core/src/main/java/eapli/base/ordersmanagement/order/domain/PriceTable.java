package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class PriceTable  implements ValueObject {
    private String PriceTableID;

    @Embedded
    private SalesRegion salesRegion;


}
