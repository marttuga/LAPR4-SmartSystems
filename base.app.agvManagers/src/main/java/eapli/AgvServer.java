package eapli;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AgvServer {

    static ServerSocket sock;
    public static void main(String args[]) throws Exception {
        try {
            sock = new ServerSocket(9999);
        } catch(IOException ex) {
            System.out.println("Falha ao abrir a server socket"); System.exit(1);
        }
        while(true) {
            Socket cliSock = sock.accept();
            new Thread(new AgvServerThread(cliSock)).start();
        }
    }
}
