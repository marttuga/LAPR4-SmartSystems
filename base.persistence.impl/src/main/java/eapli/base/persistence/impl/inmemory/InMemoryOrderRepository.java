package eapli.base.persistence.impl.inmemory;

import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.order.domain.OrderID;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.List;
import java.util.Optional;

public class InMemoryOrderRepository  extends InMemoryDomainRepository<ProductOrder, OrderID> implements OrderRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public List<ProductOrder> findAllOrders() {
        return findAllOrders();
    }

    @Override
    public List<ProductOrder> findOrdersByStatus(eapli.base.ordersmanagement.order.domain.Status status) {
        return null;
    }


    @Override
    public ProductOrder findByOrderID(String id) {
        return findByOrderID(id);
    }
}
