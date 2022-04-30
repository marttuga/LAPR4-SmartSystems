package eapli.base.ordersmanagement.shoppingCart.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
class ShoppingCartID implements ValueObject, Comparable<ShoppingCartID> {
    private String shoppingCartID;

    public ShoppingCartID(final String cartID) {
        if (StringPredicates.isNullOrEmpty(cartID)) {
            throw new IllegalArgumentException(
                    "ShoppingCart ID should neither be null nor empty");
        }
        this.shoppingCartID = cartID;
    }

    public ShoppingCartID() {
    }

    public static ShoppingCartID valueOf(final String cartID) {
        return new ShoppingCartID(cartID);
    }

    @Override
    public int hashCode() {
        return this.shoppingCartID.hashCode();
    }

    @Override
    public String toString() {
        return this.shoppingCartID;
    }

    @Override
    public int compareTo(final ShoppingCartID o) {
        return shoppingCartID.compareTo(o.shoppingCartID);
    }
}

