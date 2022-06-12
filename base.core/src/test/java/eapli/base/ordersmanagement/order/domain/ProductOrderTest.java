package eapli.base.ordersmanagement.order.domain;

import eapli.base.ordersmanagement.customer.domain.*;
import eapli.base.ordersmanagement.product.domain.Weight;
import eapli.base.ordersmanagement.shoppingCart.domain.ProductItem;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.Identifier;
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
        String expected =  "\nOrder:" +
                "\ndateTime="  + d.getTime() +
                "\norderID=" + orderID +
                "\nstatus=" + Status.REGISTERED +
                "\norderActor=" + o +
                "\npaymentMethod=" + PaymentMethod.MBWAY +
                "\nshippingMethod=" + ShippingMethod.Blue +
                "\npriceOrder=" + "total price: \n"+ priceOrder+
                "\nlineOrder=" + lineOrder +
                "\ncostumer=" + c;
        Assertions.assertEquals(expected, order.toString());
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

    @Test
    void changeStatus() {
        ProductOrder order = new ProductOrder(o, orderID, c, d, lineOrder, po, PaymentMethod.MBWAY, ShippingMethod.Blue, Status.REGISTERED);
        order.changeStatus(Status.BEING_PREPARED);
        Assertions.assertEquals(Status.BEING_PREPARED, order.getStatus());
    }

    @Test
    void changeStatus1() {
        ProductOrder order = new ProductOrder(o, orderID, c, d, lineOrder, po, PaymentMethod.MBWAY, ShippingMethod.Blue, Status.PREPARED);
        order.changeStatus(Status.DISPATCHED);
        Assertions.assertEquals(Status.DISPATCHED, order.getStatus());
    }

    @Test
    void changeStatus2() {
        ProductOrder order = new ProductOrder(o, orderID, c, d, lineOrder, po, PaymentMethod.MBWAY, ShippingMethod.Blue, Status.DISPATCHED);
        order.changeStatus(Status.DELIVERED);
        Assertions.assertEquals(Status.DELIVERED, order.getStatus());
    }

    @Test
    void changeStatus3() {
        ProductOrder order = new ProductOrder(o, orderID, c, d, lineOrder, po, PaymentMethod.MBWAY, ShippingMethod.Blue, Status.DISPATCHED);
        order.changeStatus(Status.DELIVERED);
        Assertions.assertNotEquals(Status.DISPATCHED, order.getStatus());
    }
}