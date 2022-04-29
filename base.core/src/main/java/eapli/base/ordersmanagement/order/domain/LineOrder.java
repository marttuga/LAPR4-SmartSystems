package eapli.base.ordersmanagement.order.domain;

import eapli.base.ordersmanagement.shoppingCart.domain.ProductItem;
import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import java.util.Set;

@Embeddable
public class LineOrder implements ValueObject {
private Set<ProductItem> lineOrder;
    private int priceOrderWithoutTaxes;

    public LineOrder(int priceOrderWithoutTaxes) {
        this.priceOrderWithoutTaxes = priceOrderWithoutTaxes;
    }

    public LineOrder() {
    }

    public LineOrder(Set<ProductItem> productItems, int priceOrderWithoutTaxes) {
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
