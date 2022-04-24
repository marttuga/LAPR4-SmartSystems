package eapli.base.ordersmanagement.repositories;

import eapli.base.ordersmanagement.domain.Order;
import eapli.base.ordersmanagement.domain.OrderID;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface OrderRepository extends DomainRepository<OrderID, Order> {
    /**
     * returns the order with the given id
     *
     * @param orderID
     * @return
     */
    default Optional<Order> findByID(OrderID orderID) {
        return ofIdentity(orderID);
    }
}
