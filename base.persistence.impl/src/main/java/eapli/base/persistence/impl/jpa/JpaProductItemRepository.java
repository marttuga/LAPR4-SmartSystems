package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordersmanagement.shoppingCart.domain.ProductItem;
import eapli.base.ordersmanagement.shoppingCart.domain.ProductItemID;
import eapli.base.ordersmanagement.shoppingCart.repositories.ProductItemRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaProductItemRepository extends JpaAutoTxRepository<ProductItem, ProductItemID, ProductItemID> implements ProductItemRepository {

    public JpaProductItemRepository(TransactionalContext autoTx) {
        super(autoTx, "productItemId");
    }

    public JpaProductItemRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "productItemId");
    }

}

