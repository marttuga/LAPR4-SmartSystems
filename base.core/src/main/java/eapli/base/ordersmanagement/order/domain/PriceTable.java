package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;

@Entity
public class PriceTable  implements AggregateRoot<PriceTableID>, Comparable<PriceTableID> {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private PriceTableID priceTableID;

    @Embedded
    private SalesRegion salesRegion;

    /*@Embedded
    private ProductPriceDetail productPriceDetail;
*/



    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public int compareTo(PriceTableID other) {
        return AggregateRoot.super.compareTo(other);
    }

    @Override
    public PriceTableID identity() {
        return null;
    }

    @Override
    public boolean hasIdentity(PriceTableID id) {
        return AggregateRoot.super.hasIdentity(id);
    }
}
