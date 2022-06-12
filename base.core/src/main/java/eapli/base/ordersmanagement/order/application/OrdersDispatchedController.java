package eapli.base.ordersmanagement.order.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.order.domain.ProductOrderDto;
import eapli.base.ordersmanagement.order.domain.Status;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.List;

public class OrdersDispatchedController {

    private final OrderService orderService = new OrderService();
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();
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

    public ProductOrder changeStatus(ProductOrder productOrder) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.SALES_CLERK_USER);
        productOrder.changeStatus(Status.DELIVERED);
        orderRepository.save(productOrder);
        return productOrder;
    }

    public List<ProductOrder> findOpenOrders(eapli.base.ordersmanagement.order.domain.Status status, Customer c) {
        return orderRepository.findOpenOrders(status,c);
    }

    public void dispatchOrders (ProductOrder productOrder){
        productOrder.changeStatus(eapli.base.ordersmanagement.order.domain.Status.DISPATCHED);
        orderRepository.save(productOrder);
    }

    public void printOrdersList(List<ProductOrder> orderList) {
        orderService.printOrdersList(orderList);
    }

    public eapli.base.ordersmanagement.order.domain.Status statusOrder(int options) {
        return orderService.statusOrder(options);
    }

    public int showOptionsStatusOrder() {
        return orderService.showOptionsStatusOrder();
    }
}
