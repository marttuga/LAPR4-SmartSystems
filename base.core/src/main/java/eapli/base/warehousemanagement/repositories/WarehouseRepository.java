package eapli.base.warehousemanagement.repositories;

import eapli.base.warehousemanagement.domain.WarehouseID;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface WarehouseRepository extends DomainRepository<WarehouseID,Warehouse> {
    /**
     * returns the order with the given id
     *
     * @param warehouseID
     * @return
     */
    default Optional<Warehouse> findByID(WarehouseID warehouseID) {
        return ofIdentity(warehouseID);
    }



}
