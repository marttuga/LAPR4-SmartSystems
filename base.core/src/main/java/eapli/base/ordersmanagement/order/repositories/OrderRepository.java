package eapli.base.ordersmanagement.order.repositories;

import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.order.domain.OrderID;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface OrderRepository extends DomainRepository<OrderID, ProductOrder> {
    /**
     * returns the order with the given id
     *
     * @param orderID
     * @return
     */
    default Optional<ProductOrder> findByID(OrderID orderID) {
        return ofIdentity(orderID);
    }

    Optional<ProductOrder> findOrder(OrderID orderID);
}
