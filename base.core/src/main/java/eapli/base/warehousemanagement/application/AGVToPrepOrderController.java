package eapli.base.warehousemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.order.application.OrderService;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.*;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.List;

public class AGVToPrepOrderController {
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();
    private final AGVRepository agvRepository = PersistenceContext.repositories().agv();
    private final OrderService orderService = new OrderService();
    private final AGVService agvService = new AGVService();
    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();


    public List<ProductOrder> findAllOrders() {
        return (List<ProductOrder>) orderRepository.findAllOrders();
    }
    public ProductOrder findByOrderID(String id) {
        return orderRepository.findByOrderID(id);
    }
    public List<ProductOrder> findOrdersByStatus(eapli.base.ordersmanagement.order.domain.Status status) {
        return (List<ProductOrder>) orderRepository.findOrdersByStatus(status);
    }
    public ProductOrder findOrderByAGV(String ident) {
        return orderRepository.findOrderByAGV(ident);
    }

    public List<AGV> findAllAGV() {
        return agvRepository.findAllAGV();
    }
    public AGV findByAGVID(String identifier) {
        return agvRepository.findByAGVID(identifier);
    }
    public List<AGV> findAGVByStatus(Status status) {
        return agvRepository.findAGVByStatus(status);
    }




    public AGV agvToPrepOrder(AGV agv,ProductOrder productOrder) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.WAREHOUSE_EMPLOYEE);
        agv.changeOrder(productOrder);
        agv.changeStatus(Status.OCCUPIED);
        agvRepository.save(agv);
        productOrder.changeStatus(eapli.base.ordersmanagement.order.domain.Status.BEING_PREPARED);
        orderRepository.save(productOrder);
        return agv;
    }

    public void dispatchOrders (ProductOrder productOrder){
        productOrder.changeStatus(eapli.base.ordersmanagement.order.domain.Status.DISPATCHED);
        orderRepository.save(productOrder);
    }


    public void printOrdersList(List<ProductOrder> orderList) {
        orderService.printOrdersList(orderList);
    }
    public void printAGVList(List<AGV> agvList) {
        agvService.printAGVsList(agvList);
    }

    public Status statusAGV(int options) {
        return agvService.statusAGV(options);
    }
    public int showOptionsStatusAGV() {
        return agvService.showOptionsStatusAGV();
    }
    public eapli.base.ordersmanagement.order.domain.Status statusOrder(int options) {
        return orderService.statusOrder(options);
    }
    public int showOptionsStatusOrder() {
        return orderService.showOptionsStatusOrder();
    }
}
