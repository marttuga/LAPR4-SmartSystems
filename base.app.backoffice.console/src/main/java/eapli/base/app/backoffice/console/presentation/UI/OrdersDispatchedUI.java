package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.ordersmanagement.order.application.OrdersDispatchedController;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.utilitarianClasses.Utils;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

public class OrdersDispatchedUI extends AbstractUI {

    OrdersDispatchedController ordersDispatchedController = new OrdersDispatchedController();

    @Override
    protected boolean doShow() {
        try{
            System.out.println();
            System.out.println("               ORDERS :             ");
            System.out.println();
            List<ProductOrder> ordersList = ordersDispatchedController.findAllOrders();
            ordersDispatchedController.printOrdersList(ordersList);

            eapli.base.ordersmanagement.order.domain.Status status = ordersDispatchedController.statusOrder(ordersDispatchedController.showOptionsStatusOrder());
            List<ProductOrder> productOrderList = ordersDispatchedController.findOrdersByStatus(status);

            System.out.println();
            System.out.println("               ORDERS WITH THE CHOSEN STATUS :             ");
            System.out.println();
            ordersDispatchedController.printOrdersList(productOrderList);


            String id = Utils.readLineFromConsole("Please enter the ID of the order you want to change their status to delivered: ");
            ProductOrder productOrder = ordersDispatchedController.findByOrderID(id);
            System.out.println(productOrder);

            System.out.println();
            System.out.println("               ORDERS :             ");
            System.out.println();


            System.out.println(ordersDispatchedController.changeStatus(productOrder));
        } catch (final IntegrityViolationException ex){
            System.out.println("Error change status!");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Orders that had been dispatched.";

    }
}
