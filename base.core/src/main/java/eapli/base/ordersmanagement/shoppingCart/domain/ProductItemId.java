package eapli.base.ordersmanagement.shoppingCart.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
class ProductItemID implements ValueObject, Comparable<ProductItemID> {
    private String productItemID;

    public ProductItemID(final String productIdentification) {
        if (StringPredicates.isNullOrEmpty(productIdentification)) {
            throw new IllegalArgumentException(
                    "ProductItem ID should neither be null nor empty");
        }
        this.productItemID = productIdentification;
    }

    public ProductItemID() {
    }

    public static ProductItemID valueOf(final String productID) {
        return new ProductItemID(productID);
    }

    @Override
    public int hashCode() {
        return this.productItemID.hashCode();
    }

    @Override
    public String toString() {
        return this.productItemID;
    }

    public int compareTo(final ProductItemID o) {
        return productItemID.compareTo(o.productItemID);
    }


}

