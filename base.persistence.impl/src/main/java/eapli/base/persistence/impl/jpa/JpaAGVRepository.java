package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.Identifier;

import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JpaAGVRepository extends JpaAutoTxRepository<AGV, String, String> implements AGVRepository {

    public JpaAGVRepository(TransactionalContext tx) {
        super(tx, "Identifier");
    }

    public JpaAGVRepository(String puname){
        super(puname, Application.settings().getExtendedPersistenceProperties(), "Identifier");
    }

    @Override
    public List<AGV> findAllAGV() {
        TypedQuery<AGV> query = super.createQuery("SELECT DISTINCT c FROM AGV c", AGV.class);
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public AGV findByAGVID(String identifier) {
        Query q = entityManager().createQuery("SELECT agv FROM AGV agv " +
                " WHERE agv.identifier.identifier = :identifier");
        q.setParameter("identifier", identifier);
        return (AGV) q.getSingleResult();

    }

  /*  @Override
    public List<AGV> findByAGVByStatus(String status) {
        Query q = entityManager().createQuery("SELECT agv FROM AGV agv " +
                " WHERE agv.status = :status");
        q.setParameter("status", status);
        return new ArrayList<>(q.getResultList());
    }*/

    @Override
    public Optional<AGV> ofIdentity(Identifier id) {
        return Optional.empty();
    }


    @Override
    public void deleteOfIdentity(Identifier entityId) {

    }
}
