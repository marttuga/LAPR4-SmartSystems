package eapli.base.ordersmanagement.order.repositories;

import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.order.domain.OrderID;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends DomainRepository<OrderID, ProductOrder> {
    List<ProductOrder> findAllOrders();

    List<ProductOrder> findOrdersByStatus(eapli.base.ordersmanagement.order.domain.Status status);

    ProductOrder findByOrderID(String id);

    ProductOrder findOrderByAGV(String id);

}
