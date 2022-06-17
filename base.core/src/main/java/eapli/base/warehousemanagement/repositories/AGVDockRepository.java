package eapli.base.warehousemanagement.repositories;

import eapli.base.warehousemanagement.domain.AGVDock;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface AGVDockRepository extends DomainRepository<String,AGVDock> {

    default Optional<AGVDock> findByID(String agvDockID) {
        return ofIdentity(agvDockID);
    }

}