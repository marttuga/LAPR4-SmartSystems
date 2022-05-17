package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.shoppingCart.domain.ShoppingCart;
import eapli.base.ordersmanagement.shoppingCart.domain.ShoppingCartID;
import eapli.base.ordersmanagement.shoppingCart.repositories.ShoppingCartRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class JpaShoppingCartRepository extends JpaAutoTxRepository<ShoppingCart, ShoppingCartID, ShoppingCartID> implements ShoppingCartRepository {

    public JpaShoppingCartRepository(TransactionalContext autoTx) {
        super(autoTx, "shoppingCartId");
    }

    public JpaShoppingCartRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "shoppingCartId");
    }

    @Override
    public List<ShoppingCart> findAllShoppingCarts() {
        TypedQuery<ShoppingCart> query = super.createQuery("SELECT DISTINCT c FROM ShoppingCart c", ShoppingCart.class);
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public ShoppingCart findByShopCartID(String shoppingCartID) {
        Query q = entityManager().createQuery("SELECT ord FROM ShoppingCart ord " +
                " WHERE ord.shoppingCartID.shoppingCartID = :shoppingCartID");
        q.setParameter("shoppingCartID", shoppingCartID);
        return (ShoppingCart) q.getSingleResult();
    }
}
