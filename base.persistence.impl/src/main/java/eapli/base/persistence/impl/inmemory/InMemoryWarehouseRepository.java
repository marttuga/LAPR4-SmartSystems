package eapli.base.persistence.impl.inmemory;

import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.customer.domain.CustomerId;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.domain.WarehouseID;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryWarehouseRepository extends InMemoryDomainRepository<Warehouse, WarehouseID> implements WarehouseRepository {
    static {
        InMemoryInitializer.init();
    }
}
