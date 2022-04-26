package eapli.base.ordersmanagement.shoppingCart.domain;

import eapli.base.ordersmanagement.order.domain.LineOrder;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;

@Entity
public class ProductItem implements AggregateRoot<ProductItemID>{

    private double priceItem;
    private int quantity;

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ProductItemID productItemID;

   @Embedded
    private LineOrder lineOrder;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_code")
    private Product product;

    @Override
    public String toString() {
        return "ProductItem:" +
                "priceItem=" + priceItem +
                ", quantity=" + quantity +
                ", productItemID=" + productItemID +
                ", lineOrder=" + lineOrder +
                ", product=" + product ;
    }

    @Override
    public int compareTo(ProductItemID other) {
        return AggregateRoot.super.compareTo(other);
    }

    @Override
    public ProductItemID identity() {
        return this.productItemID;
    }

    @Override
    public boolean hasIdentity(ProductItemID id) {
        return AggregateRoot.super.hasIdentity(id);
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    public ProductItemID productItemID() {
        return identity();
    }

    public ProductItemID getProductItemID() {
        return productItemID;
    }

    public LineOrder getLineOrder() {
        return lineOrder;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPriceItem() {
        return priceItem;
    }
}
