package eapli.base.warehousemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.*;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.application.ApplicationService;

@ApplicationService
public class AGVService {

    private final AGVRepository agvRepository = PersistenceContext.repositories().agv();

    public AGV createAGV(Identifier identifier, int autonomy, AGVDock agvDock, String description, Model model, MaxWeightCarry maxWeightCarry){
        final AGVBuilder agvBuilder = new AGVBuilder();
        AGV agv;
        agv = agvBuilder.build();
        agvBuilder.withIdentifier(identifier).withAutonomy(autonomy).withAGVDock(agvDock).withAGVDescription(description).withModel(model).withMaxWeightCarry(maxWeightCarry);
        this.agvRepository.save(agv);

        return agv;
    }
}
