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
import java.util.Set;

public class NewProductOrderController {
    private final OrderService orderService = new OrderService();
    private final ViewCatalogController catalogController = new ViewCatalogController();

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
        ProductPriceDetail price = (catalogController.findByProductCode(products.iterator().next().getUniqueInternalCode())).getPriceDetail();
        double productItemPrice = (price.getPrice().amountAsDouble()) * quantity;
        Money pp = Money.euros(productItemPrice);

        return new ProductItem(pp, products, quantity);
    }


    public LineOrder lineOrder(Set<ProductItem> productItens) {
        double sum = 0;
        double lp = 0;
        for (int i = 0; i < productItens.size(); i++) {
            lp = productItens.iterator().next().getPriceItem().amountAsDouble();
            sum += lp;
        }
        Money lineOrderPrice = Money.euros(lp);

        return new LineOrder(productItens, lineOrderPrice);
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


    public PriceOrder priceOfOrder(LineOrder lineOrder, SalesRegion sr,ShippingMethod sM) {
        double cost = orderService.shippingCostMethod(sM).shippingMoney().amountAsDouble()+orderService.shippingCostPlace(sr).shippingMoney().amountAsDouble();
        ShippingCost shippingCost= new ShippingCost(Money.euros(cost));
        Money priceO= Money.euros(cost);
        return new PriceOrder(priceO,shippingCost);
    }

    public OrderActor orderActor(String id) {
        return new OrderActor(id, OrderActor.Role.Sales_Clerk);
    }


}