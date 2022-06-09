package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.customer.domain.CustomerId;
import eapli.base.ordersmanagement.customer.repositories.CustomerRepository;
import eapli.base.ordersmanagement.order.domain.LineOrder;
import eapli.base.ordersmanagement.order.domain.LineOrderID;
import eapli.base.ordersmanagement.order.repositories.LineOrderRepository;
import eapli.base.ordersmanagement.shoppingCart.repositories.ProductItemRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaLineOrderRepository extends JpaAutoTxRepository<LineOrder, LineOrderID, LineOrderID> implements LineOrderRepository {

    public JpaLineOrderRepository(TransactionalContext autoTx) {
        super(autoTx, "lineOrderId");
    }
    public JpaLineOrderRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "lineOrderId");
    }

}
