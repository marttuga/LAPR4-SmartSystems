package eapli.base.persistence.impl.inmemory;

import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.order.domain.OrderID;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import java.util.Optional;

public class InMemoryOrderRepository  extends InMemoryDomainRepository<ProductOrder, OrderID> implements OrderRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Optional<ProductOrder> findOrder(OrderID orderID) {
        return matchOne(e -> e.getOrderID().equals(orderID));
    }




}
