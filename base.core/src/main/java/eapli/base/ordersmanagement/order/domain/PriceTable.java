package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;

@Entity
public class PriceTable  implements AggregateRoot<PriceTableID> {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private PriceTableID priceTableID;

    @Embedded
    private SalesRegion salesRegion;

/*
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "paymentMethod_id")
    private PaymentMethod paymentMethod;
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
