package eapli.base.warehousemanagement.repositories;

import eapli.base.warehousemanagement.domain.Aisle;
import eapli.framework.domain.repositories.DomainRepository;


public interface AisleRepository extends DomainRepository<Integer, Aisle> {

    Aisle findByID(int aisleID);

}