package eapli.base.ordersmanagement.shoppingCart.repositories;

import eapli.base.ordersmanagement.shoppingCart.domain.ProductItem;
import eapli.base.ordersmanagement.shoppingCart.domain.ProductItemID;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;


public interface ProductItemRepository extends DomainRepository<ProductItemID, ProductItem> {
    List<ProductItem> findAllProductItems();


    ProductItem findByProdItemID(String productItemID);
}
