package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.warehousemanagement.application.AGVToPrepOrderController;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.utilitarianClasses.Utils;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.Status;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;


public class AGVToPrepOrderUI extends AbstractUI {
    private static final AGVToPrepOrderController agvToPrepOrderController = new AGVToPrepOrderController();

    public boolean doShow() {
        try{
        System.out.println();
        System.out.println("               ORDERS :             ");
        System.out.println();
        List<ProductOrder> ordersList = agvToPrepOrderController.findAllOrders();
        agvToPrepOrderController.printOrdersList(ordersList);

        eapli.base.ordersmanagement.order.domain.Status status = agvToPrepOrderController.statusOrder(agvToPrepOrderController.showOptionsStatusOrder());
        List<ProductOrder> productOrderList = agvToPrepOrderController.findOrdersByStatus(status);

        System.out.println();
        System.out.println("               ORDERS WITH THE CHOSEN STATUS :             ");
        System.out.println();
        agvToPrepOrderController.printOrdersList(productOrderList);

        String id = Utils.readLineFromConsole("Please enter the ID of the order you want to start preparing: ");
        ProductOrder productOrder = agvToPrepOrderController.findByOrderID(id);
        System.out.println(productOrder);

        System.out.println();
        System.out.println("               AGVs :             ");
        System.out.println();

        List<AGV> agvList = agvToPrepOrderController.findAllAGV();
        agvToPrepOrderController.printAGVList(agvList);

        Status statusAGV = agvToPrepOrderController.statusAGV(agvToPrepOrderController.showOptionsStatusAGV());
        List<AGV> agvListt = agvToPrepOrderController.findAGVByStatus(statusAGV);
        System.out.println();
        System.out.println("               AGVS WITH THE CHOSEN STATUS :             ");
        System.out.println();
        agvToPrepOrderController.printAGVList(agvListt);

        String idAGV = Utils.readLineFromConsole("Please enter the ID of the AGV you want to start preparing the order: ");
        AGV agv2 = agvToPrepOrderController.findByAGVID(idAGV);


        System.out.println(agvToPrepOrderController.agvToPrepOrder(agv2, productOrder));
        } catch (final IntegrityViolationException ex){
            System.out.println("Error associating the agv");
        }
        return false;
    }

    @Override
    public String headline() {

        return "Give an order to prepare to an AGV";

    }


}
