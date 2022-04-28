package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.order.domain.OrderID;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JpaOrderRepository extends JpaAutoTxRepository<ProductOrder, String, String> implements OrderRepository {


    public JpaOrderRepository(TransactionalContext autoTx) {
        super(autoTx, "orderID");
    }

    public JpaOrderRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "orderID");
    }

    @Override
    public Optional<ProductOrder> findOrder(OrderID orderID) {
        final Map<String, Object> params = new HashMap<>();
        params.put("OrderIdentification", orderID);
        return matchOne("e.orderID=:OrderIdentification", params);
    }

    @Override
    public Optional<ProductOrder> ofIdentity(OrderID id) {
        return Optional.empty();
    }

    @Override
    public void deleteOfIdentity(OrderID entityId) {
    }

}
