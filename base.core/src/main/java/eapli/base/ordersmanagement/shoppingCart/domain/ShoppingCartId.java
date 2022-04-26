package eapli.base.ordersmanagement.shoppingCart.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class ShoppingCartId implements ValueObject, Comparable<ShoppingCartId>{

    private int shoppingCartId;

    @Override
    public int compareTo(ShoppingCartId o) {
        return 0;
    }
}
