package eapli.base.warehousemanagement.repositories;

import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.Identifier;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;


public interface AGVRepository extends DomainRepository<Identifier,AGV> {


    default Optional<AGV> findByID(Identifier identifier) {
        return ofIdentity(identifier);
    }
}
