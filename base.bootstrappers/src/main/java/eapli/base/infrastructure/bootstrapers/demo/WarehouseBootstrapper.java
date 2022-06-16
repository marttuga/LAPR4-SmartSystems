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
        Aisle aisle = new Aisle(1,new WarehouseID("1"),5,1,16,1,1,1,"w");
        Aisle aisle2 = new Aisle(2,new WarehouseID("1"),5,8,16,8,1,1,"w");
        Aisle aisle3 = new Aisle(3,new WarehouseID("1"),5,11,16,11,1,1,"w");
        Aisle aisle4 = new Aisle(4,new WarehouseID("1"),5,18,16,18,1,1,"w");
        List<Aisle> aisles = new ArrayList<>();

        aisles.add(aisle);
        aisleRepository.save(aisle);
        aisles.add(aisle2);
        aisleRepository.save(aisle2);
        aisles.add(aisle3);
        aisleRepository.save(aisle3);
        aisles.add(aisle4);
        aisleRepository.save(aisle4);

        AGVDock agvDock = new AGVDock("1",new WarehouseID("1"),1,3,1,3,1,1,"w");
        AGVDock agvDock2 = new AGVDock("2",new WarehouseID("1"),1,5,1,5,1,1,"w");
        AGVDock agvDock3 = new AGVDock("3",new WarehouseID("1"),1,13,1,13,1,1,"w");
        AGVDock agvDock4= new AGVDock("4",new WarehouseID("1"),20,4,20,4,1,1,"w");
        AGVDock agvDock5 = new AGVDock("5",new WarehouseID("1"),20,14,20,14,1,1,"w");


        List<AGVDock>agvDocks = new ArrayList<>();

        agvDocks.add(agvDock);
        agvDockRepository.save(agvDock);
        agvDocks.add(agvDock2);
        agvDockRepository.save(agvDock2);
        agvDocks.add(agvDock3);
        agvDockRepository.save(agvDock3);
        agvDocks.add(agvDock4);
        agvDockRepository.save(agvDock4);
        agvDocks.add(agvDock5);
        agvDockRepository.save(agvDock5);


        WarehouseID warehouseID = new WarehouseID("1");
        Plan plan = new Plan(20,18,360,"blocks");
        Row row = new Row(1,1,1,5,5);
        Shelf shelf = new Shelf(1,3);


        Warehouse warehouse = new Warehouse(warehouseID,plan,aisles,row,shelf,agvDocks);
        warehouseRepository.save(warehouse);

        return true;
    }

}
