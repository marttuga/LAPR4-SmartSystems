package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.customer.domain.*;
import eapli.base.ordersmanagement.customer.repositories.CustomerRepository;
import eapli.base.ordersmanagement.order.application.NewProductOrderController;
import eapli.base.ordersmanagement.order.domain.*;
import eapli.base.ordersmanagement.order.repositories.LineOrderRepository;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.base.ordersmanagement.product.domain.*;
import eapli.base.ordersmanagement.product.repositories.ProductRepository;
import eapli.base.ordersmanagement.shoppingCart.domain.ProductItem;
import eapli.base.ordersmanagement.shoppingCart.domain.ProductItemID;
import eapli.base.ordersmanagement.shoppingCart.repositories.ProductItemRepository;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.general.domain.model.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ProductOrderBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductOrderBootstrapper.class);
    private final NewProductOrderController newProductOrderController = new NewProductOrderController();
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();
    private final ProductRepository productRepository = PersistenceContext.repositories().products();
    //private final LineOrderRepository lineOrderRepository = PersistenceContext.repositories().lineOrders();
    private final ProductItemRepository productItemRepository = PersistenceContext.repositories().productItems();
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();

    @Override
    public boolean execute() {

        ProductOrder productOrder1 = createProductOrder("123", "555", new Date(28/6/2001), "555", 2,
                "EUR", 10L);
        assert productOrder1 != null;
        productOrder1.changeStatus(Status.TO_BE_PREPARED);
        orderRepository.save(productOrder1);
        ProductOrder productOrder2 = createProductOrder("123", "556", new Date(23/4/2008), "555", 2,
                "EUR", 10L);
        assert productOrder2 != null;
        productOrder2.changeStatus(Status.TO_BE_PREPARED);
        orderRepository.save(productOrder2);
        ProductOrder productOrder3 = createProductOrder("123", "557", new Date(23/9/2021), "555", 2,
                "EUR", 10L);
        assert productOrder3 != null;
        productOrder3.changeStatus(Status.DELIVERED);
        orderRepository.save(productOrder3);
        ProductOrder productOrder4 = createProductOrder("123", "558", new Date(23/10/2021), "555", 2,
                "EUR", 10L);
        assert productOrder4 != null;
        productOrder4.changeStatus(Status.PREPARED);
        orderRepository.save(productOrder4);
        ProductOrder productOrder5 = createProductOrder("123", "559", new Date(21/6/2021), "555", 2,
                "EUR", 10L);
        assert productOrder5 != null;
        productOrder5.changeStatus(Status.DISPATCHED);
        orderRepository.save(productOrder5);

        return true;
    }

    private ProductOrder createProductOrder(String actorId, String orderID, Date date,
                                            String productId, int amount, String currencyCode, long priceMoney) {

        try {
            Customer customer = customerRepository.findAllCustomers().get(0);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            System.out.println(calendar);
            Product product = productRepository.findByProductCode(productId);
            Set<Product> products = new HashSet<Product>();
            products.add(product);
            System.out.println(products);
            Money money = new Money(priceMoney, Currency.getInstance(currencyCode));
            System.out.println("-----MONEY-----");
            System.out.println(money);
            ProductItemID productItemID = new ProductItemID("365");
            ProductItem productItem = new ProductItem(money,amount, productItemID, products);
            productItemRepository.save(productItem);
            Set<ProductItem> productItems = new HashSet<ProductItem>();
            productItems.add(productItem);
            System.out.println("-----PRODUCTS-----");
            System.out.println(productItems);

            OrderActor orderActor = new OrderActor(actorId, OrderActor.Role.Costumer);
            System.out.println("-----ORDER ACTOR-----");
            System.out.println(orderActor);

            OrderID orderID1 = new OrderID(orderID);
            System.out.println("-----ORDER ID-----");
            System.out.println(orderID1);

            LineOrderID lineOrderID = new LineOrderID("365");
            LineOrder lineOrder = new LineOrder(lineOrderID, productItems, money);
            //lineOrderRepository.save(lineOrder);
            System.out.println("-----LINE ORDER-----");
            System.out.println(lineOrder);

            ShippingCost shippingCost = new ShippingCost(money);
            System.out.println("-----SHIPPING COST-----");
            System.out.println(shippingCost);

            PriceOrder priceOrder = new PriceOrder(money, shippingCost);
            System.out.println("-----PRICE ORDER-----");
            System.out.println(priceOrder);

            System.out.println("-----CONTROLLER-----");
            ProductOrder productOrder = newProductOrderController.registerNewOrder(orderActor, orderID1, customer, calendar, lineOrder, priceOrder,
                    PaymentMethod.MBWAY, ShippingMethod.Standart, Status.BEING_PREPARED);
            return productOrder;
        } catch (final ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", orderID);
            LOGGER.trace("Assuming existing record", e);
        }
        return null;
    }
}

