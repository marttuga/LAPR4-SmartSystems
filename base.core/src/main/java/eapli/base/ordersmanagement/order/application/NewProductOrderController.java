/*package eapli.base.ordersmanagement.order.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.customer.application.services.CustomerServices;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.order.domain.*;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.base.ordersmanagement.product.application.ViewCatalogController;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.base.ordersmanagement.product.domain.ProductPriceDetail;
import eapli.base.ordersmanagement.product.domain.UniqueInternalCode;
import eapli.base.ordersmanagement.shoppingCart.domain.ProductItem;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.Money;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Calendar;
import java.util.Set;*/

/*public class NewProductOrderController {
    private final OrderService orderService = new OrderService();
    private final ViewCatalogController catalogController= new ViewCatalogController();
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();
    private final TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();
    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();


    public ProductOrder registerNewOrder(OrderActor orderActor, OrderID orderID, Customer customer, Calendar dateTime, LineOrder lineOrder, PriceOrder priceOrder, PaymentMethod paymentMethod, ShippingMethod shippingMethod, Status status) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.SALES_CLERK_USER);

        txCtx.beginTransaction();

        ProductOrder productOrder = orderService.registerNewOrder(orderActor, orderID, customer, dateTime, lineOrder, priceOrder, paymentMethod, shippingMethod, Status.REGISTERED);

        txCtx.commit();

        return productOrder;
    }


    public ProductItem productItem(Set<Product> products, int quantity) {
            ProductPriceDetail price =(catalogController.findByProductCode(products.iterator().next().getUniqueInternalCode())).getPriceDetail() ;
            double p= price.
        ProductItem productItem = new ProductItem( products.iterator().next().price(), quantity);

        return productItem;
    }


    public PaymentMethod paymentMethod(String paymentMethod1) {

        switch (paymentMethod1) {
            case ("Mastercard"):
                return PaymentMethod.Mastercard;
            case ("PayPal"):
                return PaymentMethod.PayPal;
            case ("MBWay"):
                return PaymentMethod.MBWay;
            case ("Visa"):
                return PaymentMethod.Visa;
            case ("Venmo"):
                return PaymentMethod.Venmo;
            case ("Bank_Transaction"):
                return PaymentMethod.Bank_Transaction;
        }

        return null;
    }

    public Address address(String id, String streetNr, String doorNumber, String postalCode, String city) {
        return new Address(id, streetNr, doorNumber, postalCode, city);
    }


    public ShippingMethod shippingMethod(String shippingMethod) {
        switch (shippingMethod) {
            case ("Standard"):
                return new ShippingMethod(1.2, ShippingMethod.Method.Standard);
            case ("Blue"):
                return new ShippingMethod(1.5, ShippingMethod.Method.Blue);
            case ("Green"):
                return new ShippingMethod(1.6, ShippingMethod.Method.Green);
        }

        return null;
    }

    public OrderActor orderActor(String id) {
        return new OrderActor(id,OrderActor.Role.Sales_Clerk);
    }


 public  LineOrder lineOrder (Set<ProductItem> productItems, Money priceOrderWithoutTaxes ){

        LineOrder lineOrder= new LineOrder();

 }

    public ProductOrder addOrder(Set<Item> items, PaymentMethod paymentMethod, Address address, ShippingMethod shippingMethod, RegistrationActor registrationActor, Customer customer) {

        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.SALES_CLERK_USER);
        double totalPrice1 = 0;
        double totalPriceWithTaxes;
        for (Item item : items) {
            totalPrice1 += item.Price().amountAsDouble();
        }
        totalPriceWithTaxes = totalPrice1 + shippingMethod.Cost();
        TotalPrice totalPrice = new TotalPrice(totalPrice1, totalPriceWithTaxes);
        RegistrationDate registrationDate = new RegistrationDate(Calendar.getInstance());
        txCtx.beginTransaction();
        final ProductOrder order = createOrder(OrderStatus.REGISTERED, paymentMethod, totalPrice, address, registrationDate, registrationActor, shippingMethod, items, customer);
        this.orderRepository.save(order);
        txCtx.commit();

        return order;
    }
}*/
