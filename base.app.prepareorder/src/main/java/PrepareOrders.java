import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.order.domain.LineOrder;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.order.domain.Status;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.base.ordersmanagement.shoppingCart.domain.ProductItem;
import eapli.base.warehousemanagement.application.AGVToPrepOrderController;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.Aisle;
import eapli.base.warehousemanagement.domain.Position;
import eapli.base.warehousemanagement.domain.Sensor;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.base.warehousemanagement.repositories.AisleRepository;

import java.util.*;

public class PrepareOrders {

    private static final AGVToPrepOrderController agvToPrepOrderController = new AGVToPrepOrderController();

    private static final AisleRepository aisleRepository = PersistenceContext.repositories().aisle();

    private static final AGVRepository agvRepository = PersistenceContext.repositories().agv();

    public static void main(String[] args) {
        while (true) {
            List<ProductOrder> ordersToBePrepared = getOrdersToBePrepared();
            List<AGV> availableAgvs = getAvailableAgvs();

            //Assign order to an available agv
            AGV availableAgv;
            ProductOrder orderToPrepare;

            if (!ordersToBePrepared.isEmpty()) {
                if (!availableAgvs.isEmpty()) {
                    availableAgv = availableAgvs.get(0);
                    orderToPrepare = ordersToBePrepared.get(0);
                    agvToPrepOrderController.agvToPrepOrder(availableAgv, orderToPrepare);

                    Set<ProductItem> orderProductItems = orderToPrepare.getLineOrder().getLineOrderList();
                    List<Product> orderProductList = new ArrayList<>();

                    for (ProductItem p : orderProductItems) {
                        orderProductList.addAll(p.getProducts());
                    }

                    Map<Position, Product> path = new HashMap<>();
                    for (Product p : orderProductList) {
                        Aisle aisle = aisleRepository.findByID(p.getAisleId()).stream().findFirst().orElse(null);
                        Position aislePosition = new Position(aisle.getLsquareBegin(), aisle.getWsquareBegin());
                        path.put(aislePosition, p);
                    }

                    prepareOrder(availableAgv, path);






                } else {
                    System.out.println("Nenhum agv disponivel de momento!\n");
                }
            } else {
                System.out.println("Nenhuma order para ser preparada de momento!\n");
            }

        }
    }

    public static List<ProductOrder> getOrdersToBePrepared() {
        return agvToPrepOrderController.findOrdersByStatus(Status.TO_BE_PREPARED);
    }

    public static List<AGV> getAvailableAgvs () {
        return agvRepository.findAGVByStatus(eapli.base.warehousemanagement.domain.Status.FREE);
    }

    public static void prepareOrder (AGV agv, Map<Position, Product> path) {
        Position agvPosition = agv.getPosition();

        //-----TODO-----
        //CRIAR GRID DO WAREHOUSE
        //FAZER BFS PARA ENCONTRAR O CAMINHO
        for (Position p : path.keySet()) {

        }




    }
}
