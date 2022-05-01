package eapli.base.ordersmanagement.order.domain;

import eapli.base.ordersmanagement.customer.domain.*;
import eapli.base.ordersmanagement.product.domain.Weight;
import eapli.base.ordersmanagement.shoppingCart.domain.ProductItem;
import eapli.framework.general.domain.model.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class ProductOrderTest {
    List<ProductOrder> lista = new ArrayList<>();
    OrderActor o = new OrderActor("1", OrderActor.Role.Sales_Clerk);
    OrderID orderID = new OrderID("1");
    CustomerId customer = new CustomerId("1");
    CustomerFirstName firstName = new CustomerFirstName("maria");
    CustomerLastName lastdName = new CustomerLastName("alice");
    CustomerEmailAdress customerEmailAddress = new CustomerEmailAdress("oi@email.pt");
    CustomerPhoneNumber customerPhoneNumber = new CustomerPhoneNumber("123456789");
    CustomerVATIdentifier customerVatIdentifier = new CustomerVATIdentifier("1234567");
    Customer c = new Customer(customer, firstName, lastdName, customerEmailAddress, customerPhoneNumber, customerVatIdentifier);
    Calendar d = Calendar.getInstance();
    Set<ProductItem> l = new HashSet<>();
    Money priceSemCustos = Money.euros(2);
    LineOrder lineOrder = new LineOrder(l, priceSemCustos);
    Money shippingCost = Money.euros(3);
    Weight weight = new Weight(2);
    ShippingCost s = new ShippingCost(shippingCost, SalesRegion.AUSTRIA, weight);
    Money priceOrder = Money.euros(4);
    PriceOrder po = new PriceOrder(priceOrder, s);


    @Test
    void testToString() {
        ProductOrder order = new ProductOrder(o, orderID, c, d, lineOrder, po, PaymentMethod.MBWAY, ShippingMethod.Blue, Status.REGISTERED);
        String expected = "Order:" +
                "dateTime=" + d +
                ", orderID=" + orderID +
                ", status=" +  Status.REGISTERED +
                ", orderActor=" + o +
                ", paymentMethod=" + PaymentMethod.MBWAY +
                ", shippingMethod=" + ShippingMethod.Blue +
                ", priceOrder=" + po +
                ", lineOrder=" + lineOrder +
                ", costumer=" + c;
        Assertions.assertEquals(expected, order.toString());
    }

    @Test
    void compareTo() {
    }

    @Test
    void identity() {
    }

    @Test
    void hasIdentity() {
    }

    @Test
    void sameAs() {
    }

    @Test
    void orderID() {
    }

    @Test
    void getOrderID() {
        OrderID or = new OrderID("1");
        ProductOrder order = new ProductOrder(o, orderID, c, d, lineOrder, po, PaymentMethod.MBWAY, ShippingMethod.Blue, Status.REGISTERED);
        Assertions.assertEquals(or, order.getOrderID());

    }

    @Test
    void getStatus() {
        ProductOrder order = new ProductOrder(o, orderID, c, d, lineOrder, po, PaymentMethod.MBWAY, ShippingMethod.Blue, Status.REGISTERED);
        Assertions.assertEquals(Status.REGISTERED, order.getStatus());
    }


    @Test
    void getPaymentMethod() {
        ProductOrder order = new ProductOrder(o, orderID, c, d, lineOrder, po, PaymentMethod.MBWAY, ShippingMethod.Blue, Status.REGISTERED);
        Assertions.assertEquals(PaymentMethod.MBWAY, order.getPaymentMethod());
    }

    @Test
    void getShippingMethod() {
        ProductOrder order = new ProductOrder(o, orderID, c, d, lineOrder, po, PaymentMethod.MBWAY, ShippingMethod.Blue, Status.REGISTERED);
        Assertions.assertEquals(ShippingMethod.Blue, order.getShippingMethod());
    }

    @Test
    void getPriceOrder() {
        PriceOrder po = new PriceOrder(priceOrder, s);
        ProductOrder order = new ProductOrder(o, orderID, c, d, lineOrder, po, PaymentMethod.MBWAY, ShippingMethod.Blue, Status.REGISTERED);
        Assertions.assertEquals(po, order.getPriceOrder());
    }

    @Test
    void getLineOrder() {
        LineOrder lineOrde = new LineOrder(l, priceSemCustos);
        ProductOrder order = new ProductOrder(o, orderID, c, d, lineOrder, po, PaymentMethod.MBWAY, ShippingMethod.Blue, Status.REGISTERED);
        Assertions.assertEquals(lineOrde.toString(), order.getLineOrder().toString());
    }
}