import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.order.domain.Status;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.repositories.AGVRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class TaskExecutor implements Runnable {
    private static final AGVRepository agvRepository = PersistenceContext.repositories().agv();

    private static final OrderRepository orderRepository = PersistenceContext.repositories().orders();


    public List<Semaphore> createSemaphore(List<AGV> agvList){

        List<Semaphore> semaphores = new ArrayList<>();

        for(int i = 0; i < agvList.size(); i++){
            Semaphore semaphoreAGV = new Semaphore(1); // semaphore mutual exclusion
            semaphores.add(semaphoreAGV);
            //Semaphore semaphoreAGVDock = new Semaphore(1);
        }
        return semaphores;
    }

    public static List<Thread> createThread(List<ProductOrder> productOrders){ //Orders to be prepared

        List<Thread> threads = new ArrayList<>();

        for(int i = 0; i < productOrders.size(); i++){
            TaskExecutor taskExecutor = new TaskExecutor();
            Thread thread = new Thread(taskExecutor);
/*            thread.setName("Thread " + i);
            threads.add(thread);
            thread.start();*/


        }
        return threads;
    }

    public static List<Thread> initializeThread(List<ProductOrder> productOrders){ //Orders to be prepared

        List<Thread> threads = new ArrayList<>();

        for(int i = 0; i < productOrders.size(); i++){
            TaskExecutor taskExecutor = new TaskExecutor();
            Thread thread = new Thread(taskExecutor);
            thread.setName("Thread " + i);
            threads.add(thread);
            thread.start();


        }
        return threads;
    }


    @Override
    public void run() {
        try{
            List<AGV> agvList = agvRepository.findAGVByStatus(eapli.base.warehousemanagement.domain.Status.FREE);

            List<Semaphore> semaphores = createSemaphore(agvList);

            List<ProductOrder> productOrders = (List<ProductOrder>) orderRepository.findOrdersByStatus(Status.TO_BE_PREPARED);

            List<Thread> threads = createThread(productOrders);

            for(int i = 0; i < semaphores.size();i++){
                semaphores.get(i).acquire();
                System.out.println("Being run by " + Thread.currentThread().getName());
                for (int j = 0; j < threads.size(); j++){
                    System.out.println("Running thread " + Thread.currentThread().getName());
                    Thread.sleep(100);
                }
                semaphores.get(i).release();
            }

        } catch (Exception e){
            e.printStackTrace();
        }


    }
}