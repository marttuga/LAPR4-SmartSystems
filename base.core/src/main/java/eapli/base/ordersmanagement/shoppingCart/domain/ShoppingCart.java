package eapli.base.ordersmanagement.shoppingCart.domain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ShoppingCart implements AggregateRoot<ShoppingCart> {

    @EmbeddedId
    private ShoppingCartId shoppingCartId;

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public int compareTo(ShoppingCart other) {
        return AggregateRoot.super.compareTo(other);
    }

    @Override
    public ShoppingCart identity() {
        return null;
    }
}
