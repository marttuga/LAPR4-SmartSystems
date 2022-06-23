import eapli.base.ordersmanagement.order.domain.*;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.base.ordersmanagement.shoppingCart.domain.ProductItem;
import eapli.base.warehousemanagement.domain.*;
import eapli.base.warehousemanagement.domain.Status;

import java.util.*;
import java.util.concurrent.Semaphore;

public class TaskExecutor implements Runnable {

    private List<ProductOrder> orderList;
    private List<AGV> agvList;
    private List<Aisle> aisleList;

    private List<ProductOrder> ordersToBePrepared = new ArrayList<>();

    private AGVDock agvDock;

    private boolean running;

    public TaskExecutor(List<ProductOrder> orderList, List<AGV> agvList, List<Aisle> aisleList, AGVDock agvDock) {
        this.orderList = orderList;
        this.agvList = agvList;
        this.aisleList = aisleList;
        this.agvDock = agvDock;
        this.running = true;
        setOrdersToBePrepared(orderList);
    }

    private static final Autonomy autonomy = new Autonomy();

    private final int speed = 20;

    private final double discharchingRate = 1;


    public List<Semaphore> createSemaphore(List<AGV> agvList){

        List<Semaphore> semaphores = new ArrayList<>();

        for(int i = 0; i < agvList.size(); i++){
            Semaphore semaphoreAGV = new Semaphore(1); // semaphore mutual exclusion
            semaphores.add(semaphoreAGV);
            AGV agv = agvList.get(i);
            agvList.remove(agv);

        }
        return semaphores;
    }

    public List<Semaphore> createSemaphoreDock(List<Semaphore> semList){

        List<Semaphore> semaphoresDock = new ArrayList<>();

        for(int i = 0; i < semList.size(); i++){
            Semaphore semaphoreAGVDock = new Semaphore(1); // semaphore mutual exclusion
            semaphoresDock.add(semaphoreAGVDock);
        }
        return semaphoresDock;
    }

    @Override
    public void run() {
        try {
            while (running) {

                System.out.println("Running Thread :" + Thread.currentThread().getName());
                List<AGV> availableAgvs = setAvailableAgvs(this.agvList);
                AGV currentAgv = availableAgvs.get(0);
                ProductOrder orderToPrepare = ordersToBePrepared.get(0);

                //Agv prepare Order
                currentAgv.changeOrder(orderToPrepare);
                currentAgv.changeStatus(Status.OCCUPIED);
                orderToPrepare.changeStatus(eapli.base.ordersmanagement.order.domain.Status.BEING_PREPARED);
                System.out.println("Order " + orderToPrepare.getOrderID() + " atribuida ao agv " + currentAgv.getIdentifier());


                //Lista de produtos
                Set<ProductItem> orderProductItems = orderToPrepare.getLineOrder().getLineOrderList();
                List<Product> orderProductList = new ArrayList<>();

                for (ProductItem p : orderProductItems) {
                    orderProductList.addAll(p.getProducts());
                }

                List<Aisle> aisleList = this.aisleList;

                //Mapa com posição de cada produto da order
                Map<Position, Product> path = new HashMap<>();
                for (Product p : orderProductList) {
                    Aisle aisle = findAisleById(p.getAisleId(), aisleList);
                    Position aislePosition = new Position(aisle.getLsquareBegin(), aisle.getWsquareBegin());
                    path.put(aislePosition, p);
                }

                //Prepare Order
                prepareOrder(currentAgv, path);


                //Order Prepared
                currentAgv.changeStatus(eapli.base.warehousemanagement.domain.Status.FREE);
                orderToPrepare.changeStatus(eapli.base.ordersmanagement.order.domain.Status.PREPARED);
                System.out.println("Order " + orderToPrepare.getOrderID() + " preparada pelo agv " + currentAgv.getIdentifier());

                int discharge = autonomy.discharching(speed,discharchingRate);          //charge of AGV
                int newAutonomy = currentAgv.getAutonomy().getAutonomy() - discharge;
                currentAgv.setAutonomy(new Autonomy(newAutonomy));
                System.out.println("Autonomia do agv " + currentAgv.getIdentifier() + " diminuiu para " + currentAgv.getAutonomy().getAutonomy());

                if (newAutonomy < 20 ) {
                    System.out.println("AGV com menos de 20% de bateria. A caminho da dock!");
                    Position dockPosition = new Position(agvDock.getLsquareBeginAGVDock(), agvDock.getWsquareBeginAGVDock());
                    BFS(warehouseMatrix, visitedMatrix, currentAgv.getPosition(), dockPosition, currentAgv);
                }


                //Remove prepared order from the list
                if (!this.ordersToBePrepared.isEmpty()) {
                    this.ordersToBePrepared.remove(0);
                }

                if (this.ordersToBePrepared.isEmpty()) {
                    running = false;
                }

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }




    /*

        try{

            int batery = 100;
            int discharge;

            List<AGV> agvList = agvRepository.findAGVByStatus(eapli.base.warehousemanagement.domain.Status.FREE);

            List<Semaphore> semaphores = createSemaphore(agvList);
            List<Semaphore> semaphoresDock = createSemaphoreDock(semaphores);

            List<ProductOrder> productOrders = (List<ProductOrder>) orderRepository.findOrdersByStatus(Status.TO_BE_PREPARED);

            /*
            List<Thread> threads = createThread(productOrders);



            for(int i = 0; i < semaphores.size();i++){
                semaphores.get(i).acquire();        //blocks if necessary until a permit is available
                semaphoresDock.get(i).acquire();
                //System.out.println("Being run by " + Thread.currentThread().getName());
                for (int j = 0; j < threads.size(); j++){
                    System.out.println("Running " + Thread.currentThread().getName());
                    discharge = autonomy.discharching(speed,discharchingRate);          //charge of AGV
                    batery -= discharge;
                    Thread.sleep(100);
                    threads.get(j).join(); //Wait for a thread to finish

                    productOrders.get(j).changeStatus(Status.PREPARED);
                    orderRepository.save(productOrders.get(j));

                }
                semaphores.get(i).release();        //adds a permit
                semaphoresDock.get(i).release();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
       */
    }

    public Aisle findAisleById (int id, List<Aisle> aisleList) {
        for (Aisle a : aisleList) {
            if (a.getAisleId() == id)
                return a;
        }
        return null;
    }

    public static void prepareOrder (AGV agv, Map<Position, Product> path) throws InterruptedException {

        int[][] warehouseMatrix = getWarehouseMatrix();
        boolean[][] vMatrix = getVisitedMatrix();

        boolean[][] visitedMatrix = new boolean[vMatrix.length][];
        for(int i = 0; i < vMatrix.length; i++) {
            visitedMatrix[i] = new boolean[vMatrix[i].length];
            for (int j = 0; j < vMatrix[i].length; j++) {
                visitedMatrix[i][j] = vMatrix[i][j];
            }
        }

        for (Map.Entry<Position, Product> entry : path.entrySet()) {
            BFS(warehouseMatrix, visitedMatrix, agv.getPosition(), entry.getKey(), agv);
        }
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

        if (grid[row][col] == 0) {
            System.out.println("Posição[" + row + "][" + col + "] vai colidir. Cuidado!");
            System.out.println("Velocidade reduzida temporariamente para metade.");
            System.out.println("Velocidade: 10");
            return false;
        }

        // Otherwise
        return true;
    }

    // Function to perform the BFS traversal
    static void BFS(int grid[][], boolean vis[][], Position start, Position end, AGV agv) throws InterruptedException {

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
                System.out.println("O agv " + agv.getIdentifier() + " chegou a posição pretendida!");
                break;
            }
            int x = cell[0];
            int y = cell[1];

            System.out.print("AGV " + agv.getIdentifier() + " Position: \n");
            System.out.println("X - " + x);
            System.out.println("Y - " + y + "\n");
            agv.setPosition(new Position(x,y));

            //Sleep 2s
            //Thread.sleep(2000);

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

    public void setOrdersToBePrepared(List<ProductOrder> orderList) {
        for (ProductOrder p : orderList) {
            if (p.getStatus().equals(eapli.base.ordersmanagement.order.domain.Status.TO_BE_PREPARED))
                this.ordersToBePrepared.add(p);
        }
    }

    public List<AGV> setAvailableAgvs(List<AGV> agvList) {
        List<AGV> availableAgvs = new ArrayList<>();
        for (AGV a : agvList) {
            if (a.getStatus().equals(Status.FREE))
                availableAgvs.add(a);
        }
        return availableAgvs;
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