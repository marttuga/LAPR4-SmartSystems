package eapli.base.ordersmanagement.shoppingCart.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Quantity implements ValueObject, Comparable<Quantity>{

    private int quantity;

    @Override
    public int compareTo(Quantity o) {
        return 0;
    }
}
