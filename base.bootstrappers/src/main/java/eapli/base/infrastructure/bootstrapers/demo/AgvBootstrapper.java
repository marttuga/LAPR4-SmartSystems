package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.base.warehousemanagement.application.ConfigureAGVController;
import eapli.base.warehousemanagement.domain.*;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AgvBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(AgvBootstrapper.class);
    private final ConfigureAGVController configureAGVController = new ConfigureAGVController();
    private final AGVRepository agvRepository = PersistenceContext.repositories().agv();
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();

    @Override
    public boolean execute() {
        AGVDock agvDock = new AGVDock("555", 2, 2, 2,
                2, 2, 2, "test");
        AGV agv1 = createAgv("556", 10, agvDock, "test", "test", 10);
        assert agv1 != null;
        agv1.changeStatus(Status.FREE);
        agvRepository.save(agv1);

        AGV agv2 = createAgv("557", 10, agvDock, "test", "test", 10);
        assert agv2 != null;
        agv2.changeStatus(Status.FREE);
        agvRepository.save(agv2);

        AGV agv3 = createAgvWithOrder("558", 10, agvDock, "test", "test", 10, "558");
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
                           int maxWeightCarry) {
        try {
            AGV agv = configureAGVController.configureAGV(new Identifier(identifier), autonomy, agvDock, AGVDescription,
                    new Model(model), new MaxWeightCarry(maxWeightCarry), Status.FREE);
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
                                    int maxWeightCarry,
                                    String orderId) {
        try {
            ProductOrder order = orderRepository.findByOrderID(orderId);
            AGV agv = configureAGVController.configureAGVWithOrder(new Identifier(identifier), autonomy, agvDock, AGVDescription,
                    new Model(model), new MaxWeightCarry(maxWeightCarry), order);
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
