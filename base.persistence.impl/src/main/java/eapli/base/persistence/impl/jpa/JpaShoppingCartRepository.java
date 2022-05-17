package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordersmanagement.shoppingCart.domain.ShoppingCart;
import eapli.base.ordersmanagement.shoppingCart.domain.ShoppingCartID;
import eapli.base.ordersmanagement.shoppingCart.repositories.ShoppingCartRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaShoppingCartRepository extends JpaAutoTxRepository<ShoppingCart, ShoppingCartID, ShoppingCartID> implements ShoppingCartRepository {

    public JpaShoppingCartRepository(TransactionalContext autoTx) {
        super(autoTx, "shoppingCartId");
    }

    public JpaShoppingCartRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "shoppingCartId");
    }

}
