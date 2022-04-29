package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;

@Entity
public class PriceTable  implements AggregateRoot<PriceTableID>, Comparable<PriceTableID> {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private PriceTableID priceTableID;

    @Embedded
    private SalesRegion sRegion;

    /*@Embedded
    private ProductPriceDetail productPriceDetail;
*/

    public PriceTable(PriceTableID priceTableID, SalesRegion sRegion) {
        this.priceTableID = priceTableID;
        this.sRegion = sRegion;
    }

    public PriceTable(SalesRegion sRegion) {
        this.sRegion = sRegion;
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public int compareTo(PriceTableID other) {
        return AggregateRoot.super.compareTo(other);
    }

    @Override
    public PriceTableID identity() {
        return null;
    }

    public PriceTableID getPriceTableID() {
        return priceTableID;
    }

    public SalesRegion getsRegion() {
        return sRegion;
    }

    @Override
    public boolean hasIdentity(PriceTableID id) {
        return AggregateRoot.super.hasIdentity(id);
    }
}
