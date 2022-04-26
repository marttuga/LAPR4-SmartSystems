package eapli.base.ordersmanagement.shoppingCart.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class ProductItemId implements ValueObject, Comparable<ProductItemId>{

    private int productItemId;

    @Override
    public int compareTo(ProductItemId o) {
        return 0;
    }
}
