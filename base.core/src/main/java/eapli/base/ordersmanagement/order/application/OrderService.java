package eapli.base.ordersmanagement.order.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.order.domain.*;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.base.ordersmanagement.survey.domain.Survey;
import eapli.framework.application.ApplicationService;
import eapli.framework.general.domain.model.Money;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;

import java.util.Calendar;
import java.util.Random;

@ApplicationService
public class OrderService {
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();


    public ProductOrder registerNewOrder(OrderActor orderActor, OrderID orderID, Customer customer, Calendar dateTime, LineOrder lineOrder, PriceOrder priceOrder, PaymentMethod paymentMethod, ShippingMethod shippingMethod, Status status) {


        ProductOrder productOrder = new ProductOrder(orderActor, orderID, customer, dateTime, lineOrder, priceOrder, paymentMethod, shippingMethod, status);
        this.orderRepository.save(productOrder);
        return productOrder;
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
/*
    public ShippingMethod.Type shippingMethodShow(int options) {

        switch (options) {
            case (1):
                return ShippingMethod.Type.Standart;
            case (2):
                return ShippingMethod.Type.Blue;
            case (3):
                return ShippingMethod.Type.Green;


        }
        return null;
    }*/

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


    public ShippingCost shippingCostMethod(ShippingMethod.Type sm) {
        ShippingCost sc = null;
        if (sm.equals(ShippingMethod.Type.Standart)) {
            sc = new ShippingCost(Money.euros(1));
        } else {
            if (sm.equals(ShippingMethod.Type.Blue)) {
                sc = new ShippingCost(Money.euros(2));
            } else {
                if (sm.equals(ShippingMethod.Type.Green)) {
                    sc = new ShippingCost(Money.euros(3));

                }
            }
        }
        return sc;
    }

    public ShippingMethod shippingMethod(int options) {
        switch (options) {
            case (1):
                return new ShippingMethod(ShippingMethod.Type.Standart, shippingCostMethod(ShippingMethod.Type.Standart));
            case (2):
                return new ShippingMethod(ShippingMethod.Type.Blue, shippingCostMethod(ShippingMethod.Type.Blue));
            case (3):
                return new ShippingMethod(ShippingMethod.Type.Green, shippingCostMethod(ShippingMethod.Type.Green));
        }

        return null;
    }
}