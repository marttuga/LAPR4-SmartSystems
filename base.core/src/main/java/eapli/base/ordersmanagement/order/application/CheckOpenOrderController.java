package eapli.base.ordersmanagement.order.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.customer.repositories.CustomerRepository;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;

import java.util.List;

public class CheckOpenOrderController {
    private final OrderService orderService = new OrderService();
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();

    public void printOrdersList(List<ProductOrder> orderList) {
        orderService.printOrdersList(orderList);
    }
    public List<String> printList(List<ProductOrder> orderList) {
        return orderService.printList(orderList);
    }

    public List<ProductOrder> findOpenOrders(eapli.base.ordersmanagement.order.domain.Status status,Customer c) {
        return orderRepository.findOpenOrders(status,c);
    }
    public Customer findByCustomerEmail(String c){
        return customerRepository.findByCustomerEmail(c);
    }
}
