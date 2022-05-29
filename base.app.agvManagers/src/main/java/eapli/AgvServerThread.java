package eapli;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.base.warehousemanagement.application.AGVToPrepOrderController;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.Status;
import eapli.base.warehousemanagement.repositories.AGVRepository;

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
    private CodingAndDecoding cod = new CodingAndDecoding();

    private AGVToPrepOrderController agvToPrepOrderController = new AGVToPrepOrderController();
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();

    private final AGVRepository agvRepository = PersistenceContext.repositories().agv();
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
                AGV agv = agvToPrepOrderController.agvToPrepOrder(agvList.get(0), productOrder);
                agvRepository.save(agv);
                System.out.println("Order number + " + orderId + "assigned to AGV number" + agvList.get(0).getIdentifier());
            }

            if(message.getCode()==GETAGVSTATUS){
                String agvId = String.valueOf(message.getData_message()[0]);
                String status = String.valueOf(message.getData_message()[4]);
                AGV agv = agvRepository.findByAGVID(agvId);
                if (status.equals("FREE")) {
                    agv.changeStatus(Status.FREE);
                    System.out.println("AGV Status Changed to " + status);
                } else if (status.equals("CHARGING")) {
                    agv.changeStatus(Status.CHARGING);
                    System.out.println("AGV Status Changed to " + status);
                } else if (status.equals("OCCUPIED")) {
                    agv.changeStatus(Status.OCCUPIED);
                    System.out.println("AGV Status Changed to " + status);
                }
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
