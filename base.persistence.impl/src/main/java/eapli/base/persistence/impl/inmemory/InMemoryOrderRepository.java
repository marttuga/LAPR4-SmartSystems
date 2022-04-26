package eapli.base.persistence.impl.inmemory;

import eapli.base.ordersmanagement.order.domain.Order;
import eapli.base.ordersmanagement.order.domain.OrderID;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import java.util.Optional;

public class InMemoryOrderRepository  extends InMemoryDomainRepository<Order, OrderID> implements OrderRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Optional<Order> findOrder(OrderID orderID) {
        return matchOne(e -> e.getOrderID().equals(orderID));
    }




}
