package eapli.base.warehousemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.order.domain.SalesRegion;
import eapli.base.warehousemanagement.domain.*;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.application.ApplicationService;
import eapli.framework.io.util.Console;

import java.util.List;

@ApplicationService
public class AGVService {

    private final AGVRepository agvRepository = PersistenceContext.repositories().agv();

    public AGV createAGV(Identifier identifier, Autonomy autonomy, AGVDock agvDock, String description, Model model, MaxWeightCarry maxWeightCarry, Status status) {
        final AGVBuilder agvBuilder = new AGVBuilder();
        AGV agv;
        agv = agvBuilder.build();
        agvBuilder.withIdentifier(identifier).withAutonomy(autonomy).withAGVDock(agvDock).withAGVDescription(description).withModel(model).withMaxWeightCarry(maxWeightCarry).withStatus(status);
        this.agvRepository.save(agv);

        return agv;
    }

    public void printAGVsList(List<AGV> agvList) {
        for (AGV c : agvList) {
            System.out.println("\n"+ c.getIdentifier() +"\nDock=" + c.getAgvDock() + "\nDescription- " + c.getAGVDescription()
                    + "\nStatus- " + c.getStatus() + "\n");
        }
    }

    public void printAGV(AGV c) {
        System.out.println("\n" + c.getIdentifier() +"\nDock=" + c.getAgvDock() + "\nDescription- " + c.getAGVDescription()
                + "\nStatus- " + "\n");
    }

    public int showOptionsStatusAGV() {
        int option = -1;
        System.out.println("===================================================");
        System.out.println("               AGV STATUS:          ");
        System.out.println("=================================================\n");
        System.out.println("1-FREE");
        System.out.println("2-CHARGING");
        System.out.println("3-OCCUPIED");
        System.out.println("===================================================");
        System.out.println("0. Back\n\n");
        option = Console.readInteger("Please select an option");
        return option;
    }
    public Status statusAGV(int options) {

        switch (options) {
            case (1):
                return Status.FREE;
            case (2):
                return Status.CHARGING;
            case (3):
                return Status.OCCUPIED;
        }

        return null;
    }
}
