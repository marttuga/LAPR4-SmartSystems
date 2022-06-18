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

    public List<Thread> createThread(List<ProductOrder> productOrders){ //Orders to be prepared

        List<Thread> threads = new ArrayList<>();

        for(int i = 0; i < productOrders.size(); i++){
            TaskExecutor taskExecutor = new TaskExecutor();
            Thread thread = new Thread(taskExecutor);
            threads.add(thread);
        }
        return threads;
    }

    public List<Thread> initializeThread(List<Thread> threads){ //Orders to be prepared

        for(int i = 0; i < threads.size(); i++){
            threads.get(i).setName("Thread " + i);
            threads.get(i).start();                                     //Starts thread
        }
        return threads;
    }


    @Override
    public void run() {
        try{
            List<AGV> agvList = agvRepository.findAGVByStatus(eapli.base.warehousemanagement.domain.Status.FREE);

            List<Semaphore> semaphores = createSemaphore(agvList);
            List<Semaphore> semaphoresDock = createSemaphoreDock(semaphores);

            List<ProductOrder> productOrders = (List<ProductOrder>) orderRepository.findOrdersByStatus(Status.TO_BE_PREPARED);

            List<Thread> threads = createThread(productOrders);

            for(int i = 0; i < semaphores.size();i++){
                semaphores.get(i).acquire();
                semaphoresDock.get(i).acquire();
                System.out.println("Being run by " + Thread.currentThread().getName());
                for (int j = 0; j < threads.size(); j++){
                    System.out.println("Running thread " + Thread.currentThread().getName());
                    Thread.sleep(100);
                    threads.get(j).join();                  //Wait for a thread to finish
                }
                semaphores.get(i).release();
                semaphoresDock.get(i).release();
            }

        } catch (Exception e){
            e.printStackTrace();
        }


    }
}