package eapli.base.ordersmanagement.order.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.order.domain.*;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.base.ordersmanagement.survey.domain.Survey;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Calendar;
import java.util.Random;

@ApplicationService
public class OrderService {
        private final OrderRepository orderRepository = PersistenceContext.repositories().orders();


        public ProductOrder registerNewOrder(OrderActor orderActor, OrderID orderID,Customer customer,Calendar dateTime, LineOrder lineOrder , PriceOrder priceOrder ,PaymentMethod paymentMethod, ShippingMethod shippingMethod, Status status) {


                ProductOrder productOrder= new ProductOrder(orderActor,orderID,customer,dateTime,lineOrder,priceOrder,paymentMethod,shippingMethod,status);
                this.orderRepository.save(productOrder);
                return productOrder;
        }



}