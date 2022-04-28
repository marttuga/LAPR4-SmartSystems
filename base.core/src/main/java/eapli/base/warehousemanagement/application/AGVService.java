package eapli.base.warehousemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.*;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.application.ApplicationService;

@ApplicationService
public class AGVService {

    private final AGVRepository agvRepository = PersistenceContext.repositories().agv();

    public AGV createAGV(Identifier identifier, int autonomy, String description, Model model, MaxWeightCarry maxWeightCarry, Task task){
        final AGVBuilder agvBuilder = new AGVBuilder();
        AGV agv;
        agv = agvBuilder.build();
        agvBuilder.withIdentifier(identifier).withAutonomy(autonomy).withAGVDescription(description).withModel(model).withMaxWeightCarry(maxWeightCarry).withTask(task);
        this.agvRepository.save(agv);

        return agv;
    }
}
