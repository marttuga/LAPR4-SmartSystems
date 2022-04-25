package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordersmanagement.order.domain.Order;
import eapli.base.ordersmanagement.order.domain.OrderID;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.Optional;

public class JpaOrderRepository extends JpaAutoTxRepository<Order, String, String> implements OrderRepository {


    public JpaOrderRepository(TransactionalContext autoTx) {
        super(autoTx, "orderID");
    }

    public JpaOrderRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "orderID");
    }

    @Override
    public Optional<Order> ofIdentity(OrderID id) {
        return Optional.empty();
    }

    @Override
    public void deleteOfIdentity(OrderID entityId) {
    }
}
