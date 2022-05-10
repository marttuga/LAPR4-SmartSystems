package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.customer.domain.*;
import eapli.base.ordersmanagement.order.application.NewProductOrderController;
import eapli.base.ordersmanagement.order.domain.*;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.base.ordersmanagement.product.domain.*;
import eapli.base.ordersmanagement.product.repositories.ProductRepository;
import eapli.base.ordersmanagement.shoppingCart.domain.ProductItem;
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
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();
    private final ProductRepository productRepository = PersistenceContext.repositories().products();
    @Override
    public boolean execute() {

        createProductOrder("123", "555", new Date(23/04/2001), "555", 2, "1", 10);
        return true;
    }

    private void createProductOrder(String actorId, String orderID, Date date,
                                    String productId, int amount, String currencyCode, int priceMoney) {

        ProductOrder productOrder;
        try {
            Customer customer = new Customer(new CustomerId("222"), new CustomerFirstName("Customer1") , new CustomerLastName("CustomerL1") ,
                    new CustomerEmailAdress("customer1@gmail.com") , new CustomerPhoneNumber("999999999") , new CustomerVATIdentifier("1234567") );
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            Product product = productRepository.findByProductCode(productId);
            Set<Product> products = new HashSet<Product>();
            products.add(product);
            Money money = new Money(priceMoney, Currency.getInstance(currencyCode));
            ProductItem productItem = new ProductItem(money,products, amount);
            Set<ProductItem> productItems = new HashSet<ProductItem>();
            productItems.add(productItem);
            productOrder = newProductOrderController.registerNewOrder(new OrderActor(actorId, OrderActor.Role.Costumer),
                    new OrderID(orderID), customer, calendar, new LineOrder(productItems, money), new PriceOrder(money, new ShippingCost(money)),
                    PaymentMethod.MBWAY, ShippingMethod.Standart, Status.BEING_PREPARED);
            orderRepository.save(productOrder);
        } catch (final ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", orderID);
            LOGGER.trace("Assuming existing record", e);
        }
    }
}

