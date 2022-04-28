package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.Identifier;

import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.Optional;

public class JpaAGVRepository extends JpaAutoTxRepository<AGV, String, String> implements AGVRepository {

    public JpaAGVRepository(TransactionalContext tx) {
        super(tx, "Identifier");
    }

    public JpaAGVRepository(String puname){
        super(puname, Application.settings().getExtendedPersistenceProperties(), "Identifier");
    }


    @Override
    public Optional<AGV> ofIdentity(Identifier id) {
        return Optional.empty();
    }


    @Override
    public void deleteOfIdentity(Identifier entityId) {

    }
}
