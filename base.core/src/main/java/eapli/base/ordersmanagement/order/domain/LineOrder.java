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

    @Embedded
    @Column(insertable = false,updatable = false)
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "priceOrderWithoutTaxesAmount")),
            @AttributeOverride(name = "currency", column = @Column(name = "priceOrderWithoutTaxesCurrency"))})
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
        return "LineOrder: " +
                "\nproductItems=" + lineOrderList +
                "\npriceOrderWithoutTaxes=" + priceOrderWithoutTaxes;
    }

    public LineOrderID getLoid() {
        return loid;
    }
    public Money getPriceOrderWithoutTaxes() {
        return priceOrderWithoutTaxes;
    }

    public Set<ProductItem> getLineOrderList() {
        return lineOrderList;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public LineOrderID identity() {
        return null;
    }
}
