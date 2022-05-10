package eapli.base.ordersmanagement.shoppingCart.repositories;

import eapli.base.ordersmanagement.shoppingCart.domain.ShoppingCart;
import eapli.base.ordersmanagement.shoppingCart.domain.ShoppingCartID;
import eapli.framework.domain.repositories.DomainRepository;

public interface ShoppingCartRepository extends DomainRepository<ShoppingCartID, ShoppingCart> {
}

