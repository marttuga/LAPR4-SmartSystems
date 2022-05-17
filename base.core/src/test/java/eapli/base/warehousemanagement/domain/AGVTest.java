package eapli.base.warehousemanagement.domain;

import eapli.base.ordersmanagement.customer.domain.*;
import eapli.base.ordersmanagement.order.domain.*;
import eapli.base.ordersmanagement.product.domain.Weight;
import eapli.base.ordersmanagement.shoppingCart.domain.ProductItem;
import eapli.framework.general.domain.model.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AGVTest {

    Identifier identifier = new Identifier("2");
    Model model = new Model("x");
    MaxWeightCarry maxWeightCarry = new MaxWeightCarry(120);
    AGVDock agvDock = new AGVDock("D1", 1, 3, 1, 3, 1, 3, "l+");
    AGV agv = new AGV(identifier, 90, agvDock, "automatic", model, maxWeightCarry , Status.FREE);

    @Test
    void getIdentifier() {
        Identifier id = new Identifier("1");
        AGV agV = new AGV(id, 70,agvDock,"auto", model, maxWeightCarry , Status.FREE);
        Assertions.assertEquals(id, agV.getIdentifier());

    }

    @Test
    void getAutonomy() {
        Identifier id = new Identifier("1");
        AGV agV = new AGV(id, 70,agvDock,"auto", model, maxWeightCarry , Status.FREE);
        Assertions.assertEquals(70, agV.getAutonomy());
    }

    @Test
    void getAgvDock() {
        Identifier id = new Identifier("1");
        AGV agV = new AGV(id, 70,agvDock,"auto", model, maxWeightCarry , Status.FREE);
        Assertions.assertEquals(agvDock, agV.getAgvDock());
    }

    @Test
    void getAGVDescription() {
        Identifier id = new Identifier("1");
        AGV agV = new AGV(id, 70,agvDock,"auto", model, maxWeightCarry , Status.FREE);
        Assertions.assertEquals("auto", agV.getAGVDescription());
    }

    @Test
    void getModel() {
        Identifier id = new Identifier("1");
        AGV agV = new AGV(id, 70,agvDock,"auto", model, maxWeightCarry , Status.FREE);
        Assertions.assertEquals(model, agV.getModel());
    }

    @Test
    void getMaxWeightCarry() {
        Identifier id = new Identifier("1");
        AGV agV = new AGV(id, 70,agvDock,"auto", model, maxWeightCarry, Status.FREE );
        Assertions.assertEquals(maxWeightCarry, agV.getMaxWeightCarry());
    }

    @Test
    void changeStatus() {
        Identifier id = new Identifier("1");
        AGV agV = new AGV(id, 70,agvDock,"auto", model, maxWeightCarry, Status.FREE );
        agV.changeStatus(Status.CHARGING);
        Assertions.assertEquals(Status.CHARGING, agV.getStatus());
    }

    @Test
    void changeOrder() {
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
        Identifier id = new Identifier("1");
        ProductOrder order = new ProductOrder(o, orderID, c, d, lineOrder, po, PaymentMethod.MBWAY, ShippingMethod.Blue, eapli.base.ordersmanagement.order.domain.Status.REGISTERED);

        Identifier i = new Identifier("10");
        ProductOrder order1 = new ProductOrder(o, orderID, c, d, lineOrder, po, PaymentMethod.MBWAY, ShippingMethod.Blue, eapli.base.ordersmanagement.order.domain.Status.REGISTERED);
        AGV agV = new AGV(id, 70,agvDock,"auto", model, maxWeightCarry, Status.FREE,order );
        agV.changeOrder(order1);
        Assertions.assertEquals(order1, agV.getOrder());
    }

    @Test
    void getOrder() {
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
        Identifier id = new Identifier("1");
        ProductOrder order = new ProductOrder(o, orderID, c, d, lineOrder, po, PaymentMethod.MBWAY, ShippingMethod.Blue, eapli.base.ordersmanagement.order.domain.Status.REGISTERED);
        AGV agV = new AGV(id, 70,agvDock,"auto", model, maxWeightCarry, Status.FREE,order );
        Assertions.assertEquals(order, agV.getOrder());
    }
}