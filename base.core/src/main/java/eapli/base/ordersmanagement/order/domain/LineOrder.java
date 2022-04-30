package eapli.base.ordersmanagement.order.domain;

import eapli.base.ordersmanagement.shoppingCart.domain.ProductItem;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import eapli.framework.general.domain.model.Money;

import javax.persistence.*;
import java.util.Set;

@Entity
public class LineOrder implements AggregateRoot<LineOrderID> {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private LineOrderID loid;

    @OneToMany
    private Set<ProductItem> lineOrderList;

    private Money priceOrderWithoutTaxes;

    protected LineOrder() {
    }

    public LineOrder(Set<ProductItem> productItems, Money priceOrderWithoutTaxes) {
        this.lineOrderList = productItems;
        this.priceOrderWithoutTaxes = priceOrderWithoutTaxes;
    }

    public LineOrder(LineOrderID loid, Set<ProductItem> lineOrderList, Money priceOrderWithoutTaxes) {
        this.loid = loid;
        this.lineOrderList = lineOrderList;
        this.priceOrderWithoutTaxes = priceOrderWithoutTaxes;
    }

    @Override
    public String toString() {
        return "LineOrder:" +
                "productItems=" + lineOrderList +
                ", priceOrderWithoutTaxes=" + priceOrderWithoutTaxes;
    }

    public LineOrderID getLoid() {
        return loid;
    }

    public Set<ProductItem> getLineOrderList() {
        return lineOrderList;
    }

    public Money getPriceOrderWithoutTaxes() {
        return priceOrderWithoutTaxes;
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public LineOrderID identity() {
        return this.loid;
    }
}
