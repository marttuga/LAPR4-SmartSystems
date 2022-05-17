package eapli.base.ordersmanagement.shoppingCart.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.order.application.OrderService;
import eapli.base.ordersmanagement.order.domain.*;
import eapli.base.ordersmanagement.order.repositories.LineOrderRepository;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.base.ordersmanagement.product.application.ViewCatalogController;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.base.ordersmanagement.product.domain.ProductPriceDetail;
import eapli.base.ordersmanagement.shoppingCart.domain.ProductItem;
import eapli.base.ordersmanagement.shoppingCart.domain.ProductItemID;
import eapli.base.ordersmanagement.shoppingCart.repositories.ProductItemRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.Money;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class AddProductShopCartController {

    private final ProductItemRepository productItemRepository = PersistenceContext.repositories().productItems();
    private final LineOrderRepository lineOrderRepository = PersistenceContext.repositories().lineOrders();
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();
    private final OrderService orderService = new OrderService();
    private final ViewCatalogController catalogController = new ViewCatalogController();
    private final TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();
    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();


    public ProductOrder registerNewOrder(OrderActor orderActor, OrderID orderID, Customer customer, Calendar
            dateTime, LineOrder lineOrder, PriceOrder priceOrder, PaymentMethod paymentMethod, ShippingMethod shippingMethod, Status status) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.SALES_CLERK_USER);


        ProductOrder productOrder = new ProductOrder(orderActor, orderID, customer, dateTime, lineOrder, priceOrder, paymentMethod, shippingMethod, Status.REGISTERED);


        return orderRepository.save(productOrder);
    }

    public ProductItem productItemSet(Set<Product> products, int quantity) {
        String id = RandomStringUtils.randomAlphanumeric(6);
        ProductItemID productItemID = new ProductItemID(id);
        ProductPriceDetail price = (catalogController.findByProductCode(String.valueOf(products.iterator().next().getUniqueInternalCode()))).getPriceDetail();
        double productItemPrice = (price.getPrice().amountAsDouble()) * quantity;
        Money pp = Money.euros(productItemPrice);
        ProductItem pi = new ProductItem(pp, quantity, productItemID, products);

        return this.productItemRepository.save(pi);
    }


    public LineOrder lineOrderSet(Set<ProductItem> productItens) {
        String id = RandomStringUtils.randomAlphanumeric(6);
        LineOrderID lorderID = new LineOrderID(id);
        double sum = 0;
        double lp;
        for (ProductItem p : productItens) {
            lp = p.getPriceItem().amountAsDouble();
            sum = sum + lp;
        }
        Money lineOrderPrice = Money.euros(sum);
        LineOrder ln = new LineOrder(lorderID, productItens, lineOrderPrice);

        return this.lineOrderRepository.save(ln);
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

    public OrderActor orderActor(String email) {
        return new OrderActor(email, OrderActor.Role.Sales_Clerk);
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
