package eapli.base.warehousemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.Identifier;
import eapli.base.warehousemanagement.repositories.AGVRepository;



public class AgvRequestControllerImpl implements AgvRequestController{

    private final AGVRepository agvRepository = PersistenceContext.repositories().agv();

    @Override
    public AGV findAgv(final String id) {
        return agvRepository.findByAGVID(id);
    }
}
