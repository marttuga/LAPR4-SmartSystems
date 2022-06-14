package eapli.service;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class OrderServerService {
    private static HashMap<Socket, DataOutputStream> cliList = new HashMap<>();

    public  synchronized void sendToAll(int len, byte[] data) throws Exception {
        //System.out.println("Client Connected");
        for (DataOutputStream cOut : cliList.values()) {
            cOut.write(len);
            cOut.write(data, 0, len);

        }

    }

    public  synchronized void addCli(Socket s) throws Exception {
        cliList.put(s, new DataOutputStream(s.getOutputStream()));
    }

    public  synchronized void remCli(Socket s) throws Exception {
        cliList.get(s).write(0);
        cliList.remove(s);
        s.close();

    }
}
