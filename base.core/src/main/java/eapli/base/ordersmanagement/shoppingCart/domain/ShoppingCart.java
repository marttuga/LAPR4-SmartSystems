package eapli.base.ordersmanagement.shoppingCart.domain;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;


@Entity
public class ShoppingCart implements AggregateRoot<ShoppingCartID>{

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ShoppingCartID shoppingCartID;

    public ShoppingCart(ShoppingCartID shoppingCartID) {
        this.shoppingCartID = shoppingCartID;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_item")
    private ProductItem productItem;

    protected ShoppingCart() {
    }

    public ShoppingCart(ShoppingCartID shoppingCartID, ProductItem productItem) {
        this.productItem = productItem;
    }

    @Override
    public int compareTo(ShoppingCartID other) {
        return AggregateRoot.super.compareTo(other);
    }

    @Override
    public ShoppingCartID identity() {
        return this.shoppingCartID;
    }

    @Override
    public boolean hasIdentity(ShoppingCartID id) {
        return AggregateRoot.super.hasIdentity(id);
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    public ShoppingCartID shoppingCartID() {
        return identity();
    }

    public ShoppingCartID getShoppingCartID() {
        return shoppingCartID;
    }

    public ProductItem getProductItem() {
        return productItem;
    }
}
