package eapli.base.ordersmanagement.order.domain;

import eapli.base.ordersmanagement.shoppingCart.domain.ProductItem;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Money;

import javax.persistence.Embeddable;
import java.util.Set;

@Embeddable
public class LineOrder implements ValueObject {

private Set<ProductItem> lineOrder;

    private Money priceOrderWithoutTaxes;

    public LineOrder(Money priceOrderWithoutTaxes) {
        this.priceOrderWithoutTaxes = priceOrderWithoutTaxes;
    }

    public LineOrder() {
    }

    public LineOrder(Set<ProductItem> productItems, Money priceOrderWithoutTaxes) {
        this.lineOrder = productItems;
        this.priceOrderWithoutTaxes = priceOrderWithoutTaxes;
    }

    @Override
    public String toString() {
        return "LineOrder:" +
                "productItems=" + lineOrder +
                ", priceOrderWithoutTaxes=" + priceOrderWithoutTaxes ;
    }
}
