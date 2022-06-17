import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.order.domain.Status;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.repositories.AGVRepository;

import java.util.List;

public class SemaphoreDemo {


    private static final OrderRepository orderRepository = PersistenceContext.repositories().orders();

    private static final TaskExecutor taskExecutor = new TaskExecutor();

    public static void main(String[] args) {

        List<ProductOrder> productOrders = (List<ProductOrder>) orderRepository.findOrdersByStatus(Status.TO_BE_PREPARED);

        List<Thread> threads = taskExecutor.createThread(productOrders);
        taskExecutor.initializeThread(threads);



    }

}
