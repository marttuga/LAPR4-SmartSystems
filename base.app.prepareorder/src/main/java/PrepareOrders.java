import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.order.domain.Status;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.base.ordersmanagement.shoppingCart.domain.ProductItem;
import eapli.base.warehousemanagement.application.AGVToPrepOrderController;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.Aisle;
import eapli.base.warehousemanagement.domain.Position;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.base.warehousemanagement.repositories.AisleRepository;
import java.util.*;

public class PrepareOrders {

    private static final AGVToPrepOrderController agvToPrepOrderController = new AGVToPrepOrderController();
    private static final AisleRepository aisleRepository = PersistenceContext.repositories().aisle();
    private static final AGVRepository agvRepository = PersistenceContext.repositories().agv();
    private static final TaskExecutor taskExecutor = new TaskExecutor();

    public static void main(String[] args) {

        while (true) {
            List<ProductOrder> ordersToBePrepared = getOrdersToBePrepared();
            List<AGV> availableAgvs = getAvailableAgvs();

            List<Thread> threads = taskExecutor.createThread(ordersToBePrepared);
            taskExecutor.initializeThread(threads);

            //Assign order to an available agv
            AGV availableAgv;
            ProductOrder orderToPrepare;

            if (!ordersToBePrepared.isEmpty()) {
                if (!availableAgvs.isEmpty()) {
                    availableAgv = availableAgvs.get(0);
                    orderToPrepare = ordersToBePrepared.get(0);
                    agvToPrepOrderController.agvToPrepOrder(availableAgv, orderToPrepare);
                    System.out.println("Order " + orderToPrepare.getOrderID() + " atribuida ao agv " + availableAgv.getIdentifier());

                    Set<ProductItem> orderProductItems = orderToPrepare.getLineOrder().getLineOrderList();
                    List<Product> orderProductList = new ArrayList<>();

                    for (ProductItem p : orderProductItems) {
                        orderProductList.addAll(p.getProducts());
                    }

                    Map<Position, Product> path = new HashMap<>();
                    for (Product p : orderProductList) {
                        Aisle aisle = aisleRepository.findByID(p.getAisleId());
                        Position aislePosition = new Position(aisle.getLsquareBegin(), aisle.getWsquareBegin());
                        path.put(aislePosition, p);
                    }

                    prepareOrder(availableAgv, path);
                    availableAgv.changeStatus(eapli.base.warehousemanagement.domain.Status.FREE);
                    agvRepository.save(availableAgv);
                    System.out.println("Order " + orderToPrepare.getOrderID() + " preparada pelo agv " + availableAgv.getIdentifier());


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

    static final int ROW = 18;
    static final int COL = 20;

    // Direction vectors
    static int dRow[] = { -1, 0, 1, 0 };
    static int dCol[] = { 0, 1, 0, -1 };

    // Function to check if a cell is be visited or not
    static boolean isValid(int grid[][], boolean vis[][], int row, int col){
        // If cell lies out of bounds
        if (row < 0 || col < 0 ||
                row >= ROW || col >= COL)
            return false;

        // If cell is already visited
        if (vis[row][col])
            return false;

        //if (grid[row][col] == 1)
            //return false;

        // Otherwise
        return true;
    }

    // Function to perform the BFS traversal
    static void BFS(int grid[][], boolean vis[][], Position start, Position end, AGV agv) {

        // Stores indices of the matrix cells
        Queue<int[]> q = new LinkedList<>();

        // Mark the starting cell as visited and push it into the queue
        int[] startingCell = {start.getX(), start.getY()};
        q.add(startingCell);
        vis[start.getX()][start.getY()] = true;

        int[] endP = {end.getX(), end.getY()};
        // Iterate while the queue is not empty
        while (!q.isEmpty()) {
            int[] cell = q.peek();
            assert cell != null;
            if (cell[0] == endP[0] && cell[1] == endP[1]) {
                System.out.println("Chegou a posição pretendida!");
                break;
            }
            int x = cell[0];
            int y = cell[1];

            System.out.print("AGV Position: \n");
            System.out.println("X - " + x + "\n");
            System.out.println("Y - " + y + "\n");
            agv.setPosition(new Position(x,y));
            agvRepository.save(agv);

            q.remove();

            // Go to the adjacent cells
            for(int i = 0; i < 4; i++)
            {
                int adjx = x + dRow[i];
                int adjy = y + dCol[i];

                if (isValid(grid, vis, adjx, adjy))
                {
                    int[] block = {adjx, adjy};
                    q.add(block);
                    vis[adjx][adjy] = true;
                }
            }
        }
    }

    public static void prepareOrder (AGV agv, Map<Position, Product> path) {

        int[][] warehouseMatrix = getWarehouseMatrix();
        boolean[][] visitedMatrix = getVisitedMatrix().clone();

        for (Position p : path.keySet()) {
            BFS(warehouseMatrix, visitedMatrix, agv.getPosition(), p, agv);
        }
    }

    private static int[][] warehouseMatrix = {
            { 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
            { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1 },
    };

    private static boolean[][] visitedMatrix = {
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
    };

    public static int[][] getWarehouseMatrix() {
        return warehouseMatrix;
    }

    public static boolean[][] getVisitedMatrix() {
        return visitedMatrix;
    }
}

