import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.customer.domain.*;
import eapli.base.ordersmanagement.order.domain.*;
import eapli.base.ordersmanagement.order.domain.Status;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.base.ordersmanagement.product.domain.UniqueInternalCode;
import eapli.base.ordersmanagement.shoppingCart.domain.ProductItem;
import eapli.base.ordersmanagement.shoppingCart.domain.ProductItemID;
import eapli.base.warehousemanagement.application.AGVToPrepOrderController;
import eapli.base.warehousemanagement.domain.*;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.general.domain.model.Money;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;

public class PrepareOrders {

    public static void main(String[] args) throws IllegalAccessException {

        while (true) {
            List<ProductOrder> ordersToBePrepared = orderList();
            List<AGV> availableAgvs = agvList();

            if (!ordersToBePrepared.isEmpty()) {
                if (!availableAgvs.isEmpty()) {
                    createThreads();

                } else {
                    System.out.println("Nenhum agv disponivel de momento!\n");
                }
            } else {
                System.out.println("Nenhuma order para ser preparada de momento!\n");
            }

        }
    }

    public static void createThreads() throws IllegalAccessException {
        List<ProductOrder> orderList = orderList();
        List<AGV> agvList = agvList();
        List<Aisle> aisleList = aisleList();
        AGVDock agvDock = getAgvDock();
        TaskExecutor task = new TaskExecutor(orderList, agvList, aisleList, agvDock);
        for (int i = 0; i < agvList.size(); i++) {
            Thread t = new Thread(task);
            t.setName(String.valueOf(i));
            t.start();
        }
    }

    private static AGVDock agvDock = new AGVDock("555",new WarehouseID("1"), 2, 2, 2,
            2, 2, 2, "test");

    public static AGVDock getAgvDock() {
        return agvDock;
    }

    public static List<AGV> agvList () throws IllegalAccessException {
        List<Sensor> sensorList = new ArrayList<>();
        Sensor a = new Sensor("a", 0,1); sensorList.add(a);
        Sensor s = new Sensor("s", 1,0); sensorList.add(s);
        Sensor d = new Sensor("d", 2,1); sensorList.add(d);
        Sensor w = new Sensor("w", 1,2); sensorList.add(w);

        AGVDock agvDock = getAgvDock();

        Position inicialPosition = new Position(1,1);

        AGV agv1 = new AGV(new Identifier("555"), new Autonomy(50), agvDock, "test",
                new Model("test"), inicialPosition, sensorList, new MaxWeightCarry(100), eapli.base.warehousemanagement.domain.Status.FREE);
        AGV agv2 = new AGV(new Identifier("556"), new Autonomy(50), agvDock, "test",
                new Model("test"), inicialPosition, sensorList, new MaxWeightCarry(100), eapli.base.warehousemanagement.domain.Status.FREE);
        AGV agv3 = new AGV(new Identifier("557"), new Autonomy(50), agvDock, "test",
                new Model("test"), inicialPosition, sensorList, new MaxWeightCarry(100), eapli.base.warehousemanagement.domain.Status.FREE);
        List<AGV> agvList = new ArrayList<>();
        agvList.add(agv1);
        agvList.add(agv2);
        agvList.add(agv3);

        return agvList;
    }

    public static List<ProductOrder> orderList() throws IllegalAccessException {
        Set<CustomerPostalAddress> postalAddress = new HashSet<>();
        CustomerPostalAddress customerPostalAddress = new CustomerPostalAddress("Sunset Boulevard", 78,
                "Los angeles", "USA",90210);
        postalAddress.add(customerPostalAddress);

        String uniqueID = RandomStringUtils.randomAlphanumeric(8);
        Date date1 = new Date(Calendar.YEAR+90, Calendar.FEBRUARY, Calendar.DAY_OF_MONTH);
        Customer customer = new CustomerBuilder().withCustomerId(uniqueID).withFisrtName(
                        CustomerFirstName.valueOf("Bruno")).
                withLastName(CustomerLastName.valueOf("Goncalves")).
                withEmailAdress(CustomerEmailAdress.valueOf("bruno@gmail.com")).
                withPhoneNumber(CustomerPhoneNumber.valueOf("916218056")).
                withVATIdentifier(CustomerVATIdentifier.valueOf("0936527")).
                withBirthday(CustomerBirthDay.valueOf(date1)).
                withGender(CustomerGender.valueOf("MALE")).
                withPostalAdresses(postalAddress).build();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);

        Product product = new Product(new UniqueInternalCode("555"), 1);
        Set<Product> products = new HashSet<Product>();
        products.add(product);

        Money money = new Money(10L, Currency.getInstance("EUR"));

        ProductItemID productItemID = new ProductItemID("365");
        ProductItem productItem = new ProductItem(money,2, productItemID, products);

        Set<ProductItem> productItems = new HashSet<ProductItem>();
        productItems.add(productItem);

        OrderActor orderActor = new OrderActor("actor@gmail.com", OrderActor.Role.Costumer);

        OrderID orderID1 = new OrderID("551");
        OrderID orderID2 = new OrderID("552");
        OrderID orderID3 = new OrderID("553");
        OrderID orderID4 = new OrderID("554");
        OrderID orderID5 = new OrderID("555");

        LineOrderID lineOrderID = new LineOrderID("365");
        LineOrder lineOrder = new LineOrder(lineOrderID, productItems, money);

        ShippingCost shippingCost = new ShippingCost(money);

        PriceOrder priceOrder = new PriceOrder(money, shippingCost);

        ProductOrder productOrder1 = new ProductOrder(orderActor, orderID1, customer, calendar, lineOrder, priceOrder,
                PaymentMethod.MBWAY, ShippingMethod.Standart, eapli.base.ordersmanagement.order.domain.Status.TO_BE_PREPARED);
        ProductOrder productOrder2 = new ProductOrder(orderActor, orderID2, customer, calendar, lineOrder, priceOrder,
                PaymentMethod.MBWAY, ShippingMethod.Standart, eapli.base.ordersmanagement.order.domain.Status.TO_BE_PREPARED);
        ProductOrder productOrder3 = new ProductOrder(orderActor, orderID3, customer, calendar, lineOrder, priceOrder,
                PaymentMethod.MBWAY, ShippingMethod.Standart, eapli.base.ordersmanagement.order.domain.Status.TO_BE_PREPARED);
        ProductOrder productOrder4 = new ProductOrder(orderActor, orderID4, customer, calendar, lineOrder, priceOrder,
                PaymentMethod.MBWAY, ShippingMethod.Standart, eapli.base.ordersmanagement.order.domain.Status.TO_BE_PREPARED);
        ProductOrder productOrder5 = new ProductOrder(orderActor, orderID5, customer, calendar, lineOrder, priceOrder,
                PaymentMethod.MBWAY, ShippingMethod.Standart, eapli.base.ordersmanagement.order.domain.Status.TO_BE_PREPARED);

        List<ProductOrder> orderList = new ArrayList<>();
        orderList.add(productOrder1);
        orderList.add(productOrder2);
        //orderList.add(productOrder3);
        //orderList.add(productOrder4);
        //orderList.add(productOrder5);
        return orderList;
    }

    public static List<Aisle> aisleList () {
        Aisle aisle = new Aisle(1,new WarehouseID("1"),5,1,16,1,1,1,"w");
        Aisle aisle2 = new Aisle(2,new WarehouseID("1"),5,8,16,8,1,1,"w");
        Aisle aisle3 = new Aisle(3,new WarehouseID("1"),5,11,16,11,1,1,"w");
        Aisle aisle4 = new Aisle(4,new WarehouseID("1"),5,18,16,18,1,1,"w");
        List<Aisle> aisles = new ArrayList<>();

        aisles.add(aisle);
        aisles.add(aisle2);
        aisles.add(aisle3);
        aisles.add(aisle4);
        return aisles;
    }

}

