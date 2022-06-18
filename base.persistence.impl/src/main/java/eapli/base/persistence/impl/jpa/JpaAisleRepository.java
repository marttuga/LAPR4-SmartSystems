package eapli.base.persistence.impl.jpa;

import eapli.base.Application;

import eapli.base.warehousemanagement.domain.Aisle;
import eapli.base.warehousemanagement.repositories.AisleRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;


import javax.persistence.Query;

public class JpaAisleRepository extends JpaAutoTxRepository<Aisle, Integer, Integer > implements AisleRepository {

    public JpaAisleRepository(TransactionalContext autoTx) { super(autoTx, "aisleID");}

    public JpaAisleRepository(String puname){
        super(puname, Application.settings().getExtendedPersistenceProperties(), "aisleID");
    }

    @Override
    public Aisle findByID(int aisleID) {
        Query q = entityManager().createQuery("SELECT aisle FROM Aisle aisle " +
                " WHERE aisle.aisleId = :id");
        q.setParameter("id", aisleID);
        return (Aisle) q.getSingleResult();
    }
}
