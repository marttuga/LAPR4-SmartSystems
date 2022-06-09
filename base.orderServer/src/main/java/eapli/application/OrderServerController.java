package eapli.application;
import eapli.service.OrderServerService;
import java.net.Socket;

public class OrderServerController {
    private static final OrderServerService orderService = new OrderServerService();

    public static synchronized void sendToAll(int len, byte[] data) throws Exception {
        orderService.sendToAll(len,data);
    }
    public static synchronized void addCli(Socket s) throws Exception {
        orderService.addCli(s);
    }
    public static synchronized void remCli(Socket s) throws Exception {
        orderService.remCli(s);
    }

}
