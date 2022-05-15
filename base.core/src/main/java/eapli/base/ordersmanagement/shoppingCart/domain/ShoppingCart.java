package eapli.base.ordersmanagement.shoppingCart.domain;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;
import java.util.Set;


@Entity
public class ShoppingCart implements AggregateRoot<ShoppingCartID>{

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ShoppingCartID shoppingCartID;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Customer customer;

    public ShoppingCart(ShoppingCartID shoppingCartID) {
        this.shoppingCartID = shoppingCartID;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_item")
    private Set< ProductItem> productItem;

    protected ShoppingCart() {
    }

    public ShoppingCart(ShoppingCartID shoppingCartID, Customer customer, Set<ProductItem> productItem) {
        this.shoppingCartID = shoppingCartID;
        this.customer = customer;
        this.productItem = productItem;
    }

    @Override
    public String toString() {
        return "ShoppingCart:" +
                "\nshoppingCartID=" + shoppingCartID  +
                "\nproductItem=" + productItem +
                "\ncustomer=" + customer;
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

    public Customer getCustomer() {
        return customer;
    }

    public Set<ProductItem> getProductItem() {
        return productItem;
    }
}
