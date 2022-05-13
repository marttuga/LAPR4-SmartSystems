package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.ordersmanagement.order.application.AGVToPrepOrderController;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.utilitarianClasses.Utils;
import eapli.base.warehousemanagement.domain.AGV;
import java.util.List;


public class AGVToPrepOrderUI {
    private static final AGVToPrepOrderController agvToPrepOrderController = new AGVToPrepOrderController();

    public boolean doShow() {
        System.out.println();
        System.out.println("               ORDERS :             ");
        System.out.println();
        List<ProductOrder> ordersList = agvToPrepOrderController.findAllOrders();
        agvToPrepOrderController.printOrdersList(ordersList);

        String status = Utils.readLineFromConsole("Please enter the Status of the orders that you want to find: ");
        agvToPrepOrderController.findOrdersByStatus(status);

        String id = Utils.readLineFromConsole("Please enter the ID of the order you want to start preparing: ");
        agvToPrepOrderController.findByOrderID(id);


        System.out.println();
        System.out.println("               AGVs :             ");
        System.out.println();

        List<AGV> agvList = agvToPrepOrderController.findAllAGV();
        agvToPrepOrderController.printAGVList(agvList);

        String statusAGV = Utils.readLineFromConsole("Please enter the Status of the AGVs that you want to find: ");
        //agvToPrepOrderController.findAGVByStatus(status);

        String idAGV = Utils.readLineFromConsole("Please enter the ID of the AGV you want to start preparing the order: ");
        agvToPrepOrderController.findByAGVID(idAGV);



        return true;
    }


}
