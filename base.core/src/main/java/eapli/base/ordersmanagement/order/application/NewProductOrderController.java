package eapli.base.ordersmanagement.order.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.customer.application.services.CustomerServices;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.order.domain.*;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.base.ordersmanagement.product.domain.UniqueInternalCode;
import eapli.base.ordersmanagement.shoppingCart.domain.ProductItem;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.Money;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Calendar;

public class NewProductOrderController {
    private final OrderService orderService = new OrderService();
    private final OrderRepository catalogueRepository = PersistenceContext.repositories().orders();
    private final TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();
    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();


    public ProductOrder registerNewOrder(OrderActor orderActor, OrderID orderID, Customer customer, Calendar dateTime, LineOrder lineOrder , PriceOrder priceOrder , PaymentMethod paymentMethod, ShippingMethod shippingMethod, Status status) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.SALES_CLERK_USER);

        txCtx.beginTransaction();

        ProductOrder productOrder= orderService.registerNewOrder(orderActor,orderID,customer,dateTime,lineOrder,priceOrder,paymentMethod,shippingMethod,Status.REGISTERED);

        txCtx.commit();

        return productOrder;
    }

    public Money getPriceItem(){

    public ProductItem getProductItems(UniqueInternalCode code, int quantity){

    }
}
