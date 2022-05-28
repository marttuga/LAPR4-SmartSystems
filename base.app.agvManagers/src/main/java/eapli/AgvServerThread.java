package eapli;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.base.warehousemanagement.application.AGVToPrepOrderController;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.Status;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class AgvServerThread implements Runnable {

    public static final int ASSIGNTASK = 1;
    public static final int GETAGVSTATUS = 2;
    private Socket s;

    private DataOutputStream sOut;
    private DataInputStream sIn;
    private CodingAndDecoding cod= new CodingAndDecoding();

    private AGVToPrepOrderController agvToPrepOrderController = new AGVToPrepOrderController();
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();

    public AgvServerThread(Socket cli_s) {
        s=cli_s;
    }

    public void run() {
        InetAddress clientIP;
        clientIP = s.getInetAddress();
        System.out.println("Nova ligaçao de cliente com o ip: " + clientIP.getHostAddress() +
                ", Numero de porta: " + s.getPort());
        try {
            sIn = new DataInputStream(s.getInputStream());
            MessageDto message = cod.Decoding(sIn);

            if(message.getCode() == ASSIGNTASK){
                int orderId = message.getData_message()[0];
                ProductOrder productOrder = orderRepository.findByOrderID(String.valueOf(orderId));
                List<AGV> agvList = agvToPrepOrderController.findAGVByStatus(Status.FREE);
                agvToPrepOrderController.agvToPrepOrder(agvList.get(0), productOrder);
                System.out.println("Order number + " + orderId + "assigned to AGV number" + agvList.get(0).getIdentifier());
            }

            if(message.getCode()==GETAGVSTATUS){
                String id = String.valueOf(message.getData_message()[0]);
                System.out.println("EM DESENVOLVIMENTO!");
            }
        } catch (IOException ex) {
            System.out.println("IOException");
        }

        try {
            System.out.println("Client " + clientIP.getHostAddress() +
                    ", port number: " + s.getPort() + " disconnected");
            s.close();
        } catch (IOException ex) {
            System.out.println("IOException");
        }
    }
}
