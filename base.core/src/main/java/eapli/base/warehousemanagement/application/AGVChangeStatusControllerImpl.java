package eapli.base.warehousemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.Status;
import eapli.base.warehousemanagement.repositories.AGVRepository;

public class AGVChangeStatusControllerImpl implements  AGVChangeStatusController{

    private final AGV agv = new AGV();
    private final AGVRepository agvRepository = PersistenceContext.repositories().agv();



    @Override
    public AGV statusAGV(Status status) {
        agv.changeStatus(Status.FREE);
        agvRepository.save(agv);
        return null;
    }
}

