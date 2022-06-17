package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.base.warehousemanagement.application.ConfigureAGVController;
import eapli.base.warehousemanagement.domain.*;
import eapli.base.warehousemanagement.repositories.AGVDockRepository;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AgvBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(AgvBootstrapper.class);
    private final ConfigureAGVController configureAGVController = new ConfigureAGVController();
    private final AGVRepository agvRepository = PersistenceContext.repositories().agv();
    private final AGVDockRepository agvDockRepository = PersistenceContext.repositories().agvDock();
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();

    @Override
    public boolean execute() {
        AGVDock agvDock = new AGVDock("555",new WarehouseID("1"), 2, 2, 2,
                2, 2, 2, "test");
        agvDockRepository.save(agvDock);
        List<Sensor> sensorList = new ArrayList<>();
        Sensor a = new Sensor("a", 0,1); sensorList.add(a);
        Sensor s = new Sensor("s", 1,0); sensorList.add(s);
        Sensor d = new Sensor("d", 2,1); sensorList.add(d);
        Sensor w = new Sensor("w", 1,2); sensorList.add(w);

        AGV agv1 = createAgv("556",10 , agvDock, "test", "test", new Position(1,1),
                sensorList, 10);
        assert agv1 != null;
        agv1.changeStatus(Status.FREE);
        agvRepository.save(agv1);

        AGV agv2 = createAgv("557", 10, agvDock, "test", "test", new Position(1,1),
                sensorList,10);
        assert agv2 != null;
        agv2.changeStatus(Status.FREE);
        agvRepository.save(agv2);

        AGV agv3 = createAgvWithOrder("558", 10, agvDock, "test", "test", new Position(1,1),
                sensorList,10, "558");
        assert agv3 != null;
        agv3.changeStatus(Status.FREE);
        agvRepository.save(agv3);

        return true;
    }

    private AGV createAgv(String identifier,
                           int autonomy,
                           AGVDock agvDock,
                           String AGVDescription,
                           String model,
                           Position position,
                           List<Sensor> sensorList,
                           int maxWeightCarry) {
        try {
            AGV agv = configureAGVController.configureAGV(new Identifier(identifier), new Autonomy(autonomy), agvDock, AGVDescription,
                    new Model(model), position, sensorList, new MaxWeightCarry(maxWeightCarry));
            return agv;
        } catch (final ConcurrencyException | IllegalAccessException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", identifier);
            LOGGER.trace("Assuming existing record", e);
        }
        return null;
    }

    private AGV createAgvWithOrder(String identifier,
                                    int autonomy,
                                    AGVDock agvDock,
                                    String AGVDescription,
                                    String model,
                                    Position position,
                                    List<Sensor> sensorList,
                                    int maxWeightCarry,
                                    String orderId) {
        try {
            ProductOrder order = orderRepository.findByOrderID(orderId);
            AGV agv = configureAGVController.configureAGVWithOrder(new Identifier(identifier), new Autonomy(autonomy), agvDock, AGVDescription,
                    new Model(model), position, sensorList, new MaxWeightCarry(maxWeightCarry), order);
            return agv;
        } catch (final ConcurrencyException | IllegalAccessException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", identifier);
            LOGGER.trace("Assuming existing record", e);
        }
        return null;
    }
}
