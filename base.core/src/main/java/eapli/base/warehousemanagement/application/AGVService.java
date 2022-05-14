package eapli.base.warehousemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.warehousemanagement.domain.*;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.application.ApplicationService;

import java.util.List;

@ApplicationService
public class AGVService {

    private final AGVRepository agvRepository = PersistenceContext.repositories().agv();

    public AGV createAGV(Identifier identifier, int autonomy, AGVDock agvDock, String description, Model model, MaxWeightCarry maxWeightCarry, Status status) {
        final AGVBuilder agvBuilder = new AGVBuilder();
        AGV agv;
        agv = agvBuilder.build();
        agvBuilder.withIdentifier(identifier).withAutonomy(autonomy).withAGVDock(agvDock).withAGVDescription(description).withModel(model).withMaxWeightCarry(maxWeightCarry).withStatus(status);
        this.agvRepository.save(agv);

        return agv;
    }

    public void printAGVsList(List<AGV> agvList) {
        for (AGV c : agvList) {
            System.out.println("AGVID- " + c.getIdentifier() + "\nDescription- " + c.getAGVDescription()
                    + "\nStatus- " + c.getStatus() + "\n");
        }
    }

    public void printAGV(AGV c) {
        System.out.println("AGVID- " + c.getIdentifier() + "\nDescription- " + c.getAGVDescription()
                + "\nStatus- " + "\n");
    }

}
