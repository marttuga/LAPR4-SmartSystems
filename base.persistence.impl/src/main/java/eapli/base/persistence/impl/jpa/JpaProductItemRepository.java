package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordersmanagement.shoppingCart.domain.ProductItem;
import eapli.base.ordersmanagement.shoppingCart.domain.ProductItemID;
import eapli.base.ordersmanagement.shoppingCart.domain.ShoppingCart;
import eapli.base.ordersmanagement.shoppingCart.repositories.ProductItemRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class JpaProductItemRepository extends JpaAutoTxRepository<ProductItem, ProductItemID, ProductItemID> implements ProductItemRepository {

    public JpaProductItemRepository(TransactionalContext autoTx) {
        super(autoTx, "productItemId");
    }

    public JpaProductItemRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "productItemId");
    }

    @Override
    public List<ProductItem> findAllProductItems() {
        TypedQuery<ProductItem> query = super.createQuery("SELECT DISTINCT c FROM ProductItem c", ProductItem.class);
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public ProductItem findByProdItemID(String productItemID) {
        Query q = entityManager().createQuery("SELECT ord FROM ProductItem ord " +
                " WHERE ord.productItemID.productItemID = :productItemID");
        q.setParameter("productItemID", productItemID);
        return (ProductItem) q.getSingleResult();
    }
}

