package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class LineOrder implements ValueObject {

    private int priceOrderWithoutTaxes;

    public LineOrder(int priceOrderWithoutTaxes) {
        this.priceOrderWithoutTaxes = priceOrderWithoutTaxes;
    }

    public LineOrder() {
    }

    @Override
    public String toString() {
        return "LineOrder:" +
                "priceOrderWithoutTaxes=" + priceOrderWithoutTaxes;
    }
}
