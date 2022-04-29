package eapli.base.ordersmanagement.shoppingCart.domain;

import eapli.base.ordersmanagement.order.domain.LineOrder;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Money;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ProductItem implements AggregateRoot<ProductItemID>{

    private Money priceItem;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<Product> products= new HashSet<>();

    private int quantity;

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ProductItemID productItemID;



    @Embedded
    private LineOrder lineOrder;


    public ProductItem(Money priceItem, int quantity, ProductItemID productItemID, Set<Product> products, LineOrder lineOrder) {
        this.priceItem = priceItem;
        this.quantity = quantity;
        this.productItemID = productItemID;
        this.products = products;
        this.lineOrder = lineOrder;
    }

    protected ProductItem() {
    }

    @Override
    public String toString() {
        return "ProductItem:" +
                "priceItem=" + priceItem +
                ", quantity=" + quantity +
                ", productItemID=" + productItemID +
                ", products=" + products +
                ", lineOrder=" + lineOrder ;
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

    public Set<Product> getProducts() {
        return products;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getPriceItem() {
        return priceItem;
    }
}
