package eapli.base.ordersmanagement.shoppingCart.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.customer.domain.Customer;

import eapli.base.ordersmanagement.shoppingCart.domain.ProductItem;
import eapli.base.ordersmanagement.shoppingCart.domain.ShoppingCart;

import eapli.base.ordersmanagement.shoppingCart.domain.ShoppingCartID;
import eapli.base.ordersmanagement.shoppingCart.repositories.ShoppingCartRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.domain.repositories.TransactionalContext;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import java.util.List;
import java.util.Set;

public class AddProductShopCartController {

    private final ShoppingCartRepository shoppingCartRepo = PersistenceContext.repositories().shoppingCarts();
    private final TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();
    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();


    public ShoppingCart addProdShopCart(ShoppingCartID shoppingCartID, Customer customer, Set<ProductItem> productItem) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.CLIENT_USER);


        ShoppingCart shoppingCart = new ShoppingCart(shoppingCartID,  customer,  productItem);


        return shoppingCartRepo.save(shoppingCart);
    }


    public List<ShoppingCart> findAllShoppingCarts() {
        return shoppingCartRepo.findAllShoppingCarts();
    }

    public ShoppingCart findByShopCartID(String id) {
        return shoppingCartRepo.findByShopCartID(id);
    }
}
