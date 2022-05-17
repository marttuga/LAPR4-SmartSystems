package eapli.base.warehousemanagement.repositories;

import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.Identifier;
import eapli.base.warehousemanagement.domain.Status;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;
import java.util.Optional;


public interface AGVRepository extends DomainRepository<Identifier,AGV> {

    List<AGV> findAllAGV();
    AGV findByAGVID(String id);
    List<AGV> findAGVByStatus(Status status);
}
