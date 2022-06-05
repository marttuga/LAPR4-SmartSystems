package eapli;


import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.base.warehousemanagement.application.AGVToPrepOrderController;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.Status;
import eapli.base.warehousemanagement.repositories.AGVRepository;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class AgvServerThread implements Runnable {

    private Socket s;
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
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            DataInputStream in = new DataInputStream(s.getInputStream());
            int option = in.readInt();

            if(option == 1){
                int orderId = in.readInt();
                System.out.println("Order ID: " + orderId);
                ProductOrder productOrder = orderRepository.findByOrderID(String.valueOf(orderId));

                List<AGV> agvList = agvToPrepOrderController.findAGVByStatus(Status.FREE);
                AGV agv = agvToPrepOrderController.agvToPrepOrder(agvList.get(0), productOrder);
                agvRepository.save(agv);
                System.out.println("Order number + " + orderId + "assigned to AGV number" + agvList.get(0).getIdentifier().toString());
                String str = agvList.get(0).getIdentifier().toString();
                byte[] data = str.getBytes(StandardCharsets.UTF_8);
                out.writeInt(data.length);
                out.write(data);
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
