package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordersmanagement.warehousemanagement.domain.Warehouse;
import eapli.base.ordersmanagement.warehousemanagement.domain.WarehouseID;
import eapli.base.ordersmanagement.warehousemanagement.repositories.WarehouseRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.Optional;

public class JpaWarehouseRepository extends JpaAutoTxRepository<Warehouse, String, String > implements WarehouseRepository {

    public JpaWarehouseRepository(TransactionalContext autoTx) { super(autoTx, "warehouseID");}

    public JpaWarehouseRepository(String puname){
        super(puname, Application.settings().getExtendedPersistenceProperties(), "warehouseID");
    }

    @Override
    public Optional<Warehouse> ofIdentity(WarehouseID id) {
        return Optional.empty();
    }

    @Override
    public void deleteOfIdentity(WarehouseID entityId) {

    }
}
