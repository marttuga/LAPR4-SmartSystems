package eapli.base.ordersmanagement.shoppingCart.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class PriceItem implements ValueObject, Comparable<PriceItem>{

    private int priceItem;

    @Override
    public int compareTo(PriceItem o) {
        return 0;
    }

}
