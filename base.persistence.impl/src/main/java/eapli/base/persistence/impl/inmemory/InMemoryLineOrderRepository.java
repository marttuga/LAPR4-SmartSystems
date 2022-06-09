package eapli.base.persistence.impl.inmemory;

import eapli.base.ordersmanagement.order.domain.LineOrder;
import eapli.base.ordersmanagement.order.domain.LineOrderID;
import eapli.base.ordersmanagement.order.domain.OrderID;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
//import eapli.base.ordersmanagement.order.repositories.LineOrderRepository;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.nio.file.LinkOption;

public class InMemoryLineOrderRepository extends InMemoryDomainRepository<LineOrder, LineOrderID>  {
    static {
        InMemoryInitializer.init();
    }
}
