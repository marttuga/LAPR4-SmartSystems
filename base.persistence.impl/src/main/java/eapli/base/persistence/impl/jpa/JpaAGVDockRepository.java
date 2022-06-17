package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.warehousemanagement.domain.AGVDock;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.domain.WarehouseID;

import eapli.base.warehousemanagement.repositories.AGVDockRepository;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;


import java.util.Optional;

public class JpaAGVDockRepository extends JpaAutoTxRepository<AGVDock, String, String > implements AGVDockRepository {

    public JpaAGVDockRepository(TransactionalContext autoTx) { super(autoTx, "agvDockID");}

    public JpaAGVDockRepository(String puname){
        super(puname, Application.settings().getExtendedPersistenceProperties(), "agvDockID");
    }

    public Optional<AGVDock> findById(String agvDockID){
        return Optional.empty();
    }
}
