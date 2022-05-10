package eapli.base.ordersmanagement.order.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.order.domain.*;

import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.base.ordersmanagement.product.application.ViewCatalogController;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.base.ordersmanagement.product.domain.ProductPriceDetail;
import eapli.base.ordersmanagement.shoppingCart.domain.ProductItem;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.Money;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class NewProductOrderController {
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();
    private final OrderService orderService = new OrderService();
    private final ViewCatalogController catalogController = new ViewCatalogController();
    private final TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();
    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();


    public ProductOrder registerNewOrder(OrderActor orderActor, OrderID orderID, Customer customer, Calendar
            dateTime, LineOrder lineOrder, PriceOrder priceOrder, PaymentMethod paymentMethod, ShippingMethod shippingMethod, Status status) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.SALES_CLERK_USER);
        txCtx.beginTransaction();

        ProductOrder productOrder = orderService.registerNewOrder(orderActor, orderID, customer, dateTime, lineOrder, priceOrder, paymentMethod, shippingMethod, Status.REGISTERED);
        this.orderRepository.save(productOrder);
        txCtx.commit();

        return productOrder;
    }

    public ProductItem productItemSet(Set<Product> products, int quantity) {
        ProductPriceDetail price = (catalogController.findByProductCode(String.valueOf(products.iterator().next().getUniqueInternalCode()))).getPriceDetail();
        double productItemPrice = (price.getPrice().amountAsDouble()) * quantity;
        Money pp = Money.euros(productItemPrice);

        return new ProductItem(pp, products, quantity);
    }


    public LineOrder lineOrderSet(Set<ProductItem> productItens) {
        Random rand = new Random();
        String id = String.valueOf(rand.nextInt(999999999));
        LineOrderID lorderID = new LineOrderID(id);
        double sum = 0;
        double lp;
        for (ProductItem p : productItens) {
            lp = p.getPriceItem().amountAsDouble();
            sum = sum + lp;
        }
        Money lineOrderPrice = Money.euros(sum);

        return new LineOrder(lorderID, productItens, lineOrderPrice);
    }

    public PaymentMethod paymentMethod(int options) {
        return orderService.paymentMethod(options);
    }

    public SalesRegion salesRegion(int options) {
        return orderService.salesRegion(options);
    }

    public ShippingMethod shippingMethod(int options) {
        return orderService.shippingMethod(options);
    }


    public PriceOrder priceOfOrder(LineOrder lineOrder, SalesRegion sr, ShippingMethod sM) {
        double cost = orderService.shippingCostMethod(sM).shippingMoney().amountAsDouble() + orderService.shippingCostPlace(sr).shippingMoney().amountAsDouble() + lineOrder.getPriceOrderWithoutTaxes().amountAsDouble();
        ShippingCost shippingCost = new ShippingCost(Money.euros(cost));
        Money priceO = Money.euros(cost);
        return new PriceOrder(priceO, shippingCost);
    }

    public OrderActor orderActor(String id) {
        return new OrderActor(id, OrderActor.Role.Sales_Clerk);
    }

    public int showOptionsPayment() {
        return orderService.showOptionsPayment();
    }

    public int showOptionsShipping() {
        return orderService.showOptionsShipping();
    }

    public int showSalesRegion() {
        return orderService.showSalesRegion();
    }

    public List<ProductOrder> findAllOrders() {
        return orderRepository.findAllOrders();
    }

    public ProductOrder findByOrderID(String id) {
        return orderRepository.findByOrderID(id);
    }

}
