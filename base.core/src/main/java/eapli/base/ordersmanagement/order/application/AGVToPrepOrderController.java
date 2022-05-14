package eapli.base.ordersmanagement.order.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.base.warehousemanagement.application.AGVService;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.repositories.AGVRepository;

import java.util.List;

public class AGVToPrepOrderController {
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();
    private final AGVRepository agvRepository = PersistenceContext.repositories().agv();
    private final OrderService orderService = new OrderService();
    private final AGVService agvService = new AGVService();

    public List<ProductOrder> findAllOrders() {
        return orderRepository.findAllOrders();
    }
    public ProductOrder findByOrderID(String id) {
        return orderRepository.findByOrderID(id);
    }
    public List<ProductOrder> findOrdersByStatus(String status) {
        return orderRepository.findOrdersByStatus(status);
    }
    public List<AGV> findAllAGV() {
        return agvRepository.findAllAGV();
    }
    public AGV findByAGVID(String identifier) {
        return agvRepository.findByAGVID(identifier);
    }
    public List<AGV> findAGVByStatus(String status) {
        return agvRepository.findAGVByStatus(status);
    }

    public void printOrdersList(List<ProductOrder> orderList) {
        orderService.printOrdersList(orderList);
    }
    public void printAGVList(List<AGV> agvList) {
        agvService.printAGVsList(agvList);
    }
}
