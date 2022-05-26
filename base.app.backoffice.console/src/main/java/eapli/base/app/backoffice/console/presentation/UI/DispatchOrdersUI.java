package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.order.domain.Status;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.base.utilitarianClasses.Utils;
import eapli.base.warehousemanagement.application.AGVToPrepOrderController;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;


public class DispatchOrdersUI extends AbstractUI {

    private static final AGVToPrepOrderController controller = new AGVToPrepOrderController();



    @Override
    public boolean doShow() {
        try {
            System.out.println("=================================================================================");
            System.out.println("=======These are the orders ready to be dispatched for customer delivery!========");
            System.out.println("=================================================================================");
            List<ProductOrder> productOrderList = controller.findOrdersByStatus(Status.PREPARED);
            controller.printOrdersList(productOrderList);
            System.out.println("=================================================================================");
            if (Utils.confirm("Do you wish to filter by an AGV?")){
                System.out.println("=================================================================================");
                String idAGV = Utils.readLineFromConsole("Please enter its ID: ");
                System.out.println("=================================================================================");
                AGV agv = controller.findByAGVID(idAGV);
                ProductOrder productOrderByAgv = controller.findOrderByAGV(agv.getIdentifier().toString());
                System.out.println(productOrderByAgv.toString());
                System.out.println("=================================================================================");
                if (Utils.confirm("Do you wish to dispatch this order?")){
                   controller.dispatchOrders(productOrderByAgv);
                   System.out.println("=================================================================================");
                   System.out.println("Order has been dispatched for customer delivery!");
                }
            }else {
                System.out.println("=================================================================================");
                String id = Utils.readLineFromConsole("Please select the order you want to be dispatched for customer delivery: (Insert ID) ");
                System.out.println("=================================================================================");
                ProductOrder productOrder = controller.findByOrderID(id);
                System.out.println(productOrder);
                System.out.println("=================================================================================");
                if (Utils.confirm("Do you wish to dispatch this order?")){
                    controller.dispatchOrders(productOrder);
                    System.out.println("=================================================================================");
                    System.out.println("Order has been dispatched for customer delivery!");
                }

            }
            return true;
        } catch (Exception e) {
            System.out.println("Something went wrong");
            return false;
        }
    }

    @Override
    public String headline() {
        return null;
    }
}

