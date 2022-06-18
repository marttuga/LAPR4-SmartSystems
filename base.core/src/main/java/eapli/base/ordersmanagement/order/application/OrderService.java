package eapli.base.ordersmanagement.order.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.order.domain.*;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.framework.application.ApplicationService;
import eapli.framework.general.domain.model.Money;
import eapli.framework.io.util.Console;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@ApplicationService
public class OrderService {
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();


    public void printOrdersList(List<ProductOrder> orderList) {
        for (ProductOrder c : orderList) {
            System.out.println("orderID- " + c.toDTO().getOrderID() + "\nCustomerID- "
                    + "\nStatus- " + c.toDTO().getStatusDto()  + "\nDate- " + c.getDateTime() +"\n");
        }
    }
    public List<ProductOrderDto> printList(List<ProductOrder> orderList) {
        ProductOrderDto s;
        List<ProductOrderDto> o = new ArrayList<>();
        for (ProductOrder c : orderList) {
            s=new ProductOrderDto( c.toDTO().getOrderID() , c.toDTO().getStatusDto() );
            o.add(s);
        }
        return o;
    }
    public void printOrder(ProductOrder c) {
            System.out.println("orderID- " + c.toDTO().getOrderID() + "\nCustomerID- "
                    + "\nStatus- " + c.toDTO().getStatusDto() + "\nDate- " + c.getDateTime() + "\n" );

    }

    public int showOptionsPayment() {
        int option = -1;
        System.out.println("===================================================");
        System.out.println("               Payment Methods:          ");
        System.out.println("=================================================\n");
        System.out.println("1-PAY_PAL");
        System.out.println("2-MBWAY");
        System.out.println("3-CREDIT_CARD");
        System.out.println("4-BANK_TRANSFERS");
        System.out.println("5-VENMO");
        System.out.println("===================================================");
        System.out.println("0. Back\n\n");
        option = Console.readInteger("Please select an option");
        return option;
    }

    public int showOptionsShipping() {
        int option = -1;
        System.out.println("===================================================");
        System.out.println("               Shipping Methods:          ");
        System.out.println("=================================================\n");
        System.out.println("1-STANDART");
        System.out.println("2-BLUE");
        System.out.println("3-GREEN");
        System.out.println("===================================================");
        System.out.println("0. Back\n\n");
        option = Console.readInteger("Please select an option");
        return option;
    }

    public int showSalesRegion() {
        int option = -1;
        System.out.println("===================================================");
        System.out.println("               Sales Region:          ");
        System.out.println("=================================================\n");
        System.out.println("1-PORTUGAL");
        System.out.println("2-SPAIN");
        System.out.println("3-FRANCE");
        System.out.println("4-ITALY");
        System.out.println("5-AUSTRIA");
        System.out.println("6-FINLAND");
        System.out.println("7-GERMANY");
        System.out.println("8-MALTA");
        System.out.println("===================================================");
        System.out.println("0. Back\n\n");
        option = Console.readInteger("Please select an option");
        return option;
    }


    public PaymentMethod paymentMethod(int options) {

        switch (options) {
            case (1):
                return PaymentMethod.PAY_PAL;
            case (2):
                return PaymentMethod.MBWAY;
            case (3):
                return PaymentMethod.CREDIT_CARD;
            case (4):
                return PaymentMethod.BANK_TRANSFERS;
            case (5):
                return PaymentMethod.VENMO;
        }

        return null;
    }

    public ShippingMethod shippingMethod(int options) {
        switch (options) {
            case (1):
                return ShippingMethod.Standart;
            case (2):
                return ShippingMethod.Blue;
            case (3):
                return ShippingMethod.Green;
        }

        return null;
    }

    public SalesRegion salesRegion(int options) {

        switch (options) {
            case (1):
                return SalesRegion.PORTUGAL;
            case (2):
                return SalesRegion.SPAIN;
            case (3):
                return SalesRegion.FRANCE;
            case (4):
                return SalesRegion.ITALY;
            case (5):
                return SalesRegion.AUSTRIA;
            case (6):
                return SalesRegion.FINLAND;
            case (7):
                return SalesRegion.GERMANY;
            case (8):
                return SalesRegion.MALTA;
        }

        return null;
    }

    public ShippingCost shippingCostPlace(SalesRegion sr) {
        ShippingCost sc = null;
        if (sr.equals(SalesRegion.PORTUGAL) || sr.equals(SalesRegion.SPAIN)) {
            sc = new ShippingCost(Money.euros(0.5), sr);
        } else {
            if (sr.equals(SalesRegion.FRANCE) || sr.equals(SalesRegion.ITALY)) {
                sc = new ShippingCost(Money.euros(1.0), sr);
            } else {
                if (sr.equals(SalesRegion.AUSTRIA) || sr.equals(SalesRegion.GERMANY)) {
                    sc = new ShippingCost(Money.euros(1.5), sr);
                } else {
                    if (sr.equals(SalesRegion.MALTA) || sr.equals(SalesRegion.FINLAND)) {
                        sc = new ShippingCost(Money.euros(2.0), sr);
                    }

                }
            }
        }
        return sc;
    }


    public ShippingCost shippingCostMethod(ShippingMethod sm) {
        ShippingCost sc = null;
        if (sm.equals(ShippingMethod.Standart)) {
            sc = new ShippingCost(Money.euros(1));
        } else {
            if (sm.equals(ShippingMethod.Blue)) {
                sc = new ShippingCost(Money.euros(2));
            } else {
                if (sm.equals(ShippingMethod.Green)) {
                    sc = new ShippingCost(Money.euros(3));

                }
            }
        }
        return sc;
    }

    public int showOptionsStatusOrder() {
        int option = -1;
        System.out.println("===================================================");
        System.out.println("                    ORDER STATUS:                  ");
        System.out.println("===================================================\n");
        System.out.println("1-REGISTERED");
        System.out.println("2-TO BE PREPARED");
        System.out.println("3-BEING PREPARED");
        System.out.println("4-PREPARED");
        System.out.println("5-DISPATCHED");
        System.out.println("6-DELIVERED");
        System.out.println("===================================================");
        System.out.println("0. Back\n\n");
        option = Console.readInteger("Please select an option");
        return option;
    }
    public eapli.base.ordersmanagement.order.domain.Status statusOrder(int options) {

        switch (options) {
            case (1):
                return eapli.base.ordersmanagement.order.domain.Status.REGISTERED;
            case (2):
                return eapli.base.ordersmanagement.order.domain.Status.TO_BE_PREPARED;
            case (3):
                return Status.BEING_PREPARED;
            case (4):
                return Status.PREPARED;
            case (5):
                return Status.DISPATCHED;
            case (6):
                return Status.DELIVERED;
        }

        return null;
    }


}