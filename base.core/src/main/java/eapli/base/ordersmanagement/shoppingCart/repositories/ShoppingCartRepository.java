package eapli.base.ordersmanagement.shoppingCart.repositories;

import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.shoppingCart.domain.ShoppingCart;
import eapli.base.ordersmanagement.shoppingCart.domain.ShoppingCartID;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface ShoppingCartRepository extends DomainRepository<ShoppingCartID, ShoppingCart> {
    List<ShoppingCart> findAllShoppingCarts();


    ShoppingCart findByShopCartID(String id);
}

