package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.*;

import eapli.base.warehousemanagement.repositories.AGVDockRepository;
import eapli.base.warehousemanagement.repositories.AisleRepository;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class WarehouseBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(
            WarehouseBootstrapper.class);

    WarehouseRepository warehouseRepository = PersistenceContext.repositories().warehouse();

    private final AGVDockRepository agvDockRepository = PersistenceContext.repositories().agvDock();
    private final AisleRepository aisleRepository = PersistenceContext.repositories().aisle();


    public boolean execute() {
        Aisle aisle = new Aisle(1,5,1,5,1,1,1,"accessible");
        List<Aisle> aisles = new ArrayList<>();
        AGVDock agvDock = new AGVDock("Dock1",1,3,1,3,1,1,"accessible");
        List<AGVDock>agvDocks = new ArrayList<>();
        aisles.add(aisle);
        aisleRepository.save(aisle);
        agvDockRepository.save(agvDock);
        WarehouseID warehouseID = new WarehouseID("1");
        Plan plan = new Plan(20,18,40,"cm");
        Row row = new Row(1,1,1,5,5);
        Shelf shelf = new Shelf(1,3);


        Warehouse warehouse = new Warehouse(warehouseID,plan,aisles,row,shelf,agvDocks);
        warehouseRepository.save(warehouse);

        return true;
    }

}
