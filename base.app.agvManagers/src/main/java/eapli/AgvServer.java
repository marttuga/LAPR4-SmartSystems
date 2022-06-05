package eapli;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class AgvServer {

    //AGV agv = new AGV();

    static byte[] STATUS = new byte[] {1, 12, 0,0};

    static ServerSocket sock;

    private static HashMap<Socket, DataOutputStream> cliList = new HashMap<>();

    public static synchronized void sendToAll(int len, byte[] data) throws Exception {
        for (DataOutputStream cOut : cliList.values()) {
            cOut.write(len);
            cOut.write(data, 0, len);
        }

    }

    public static synchronized void addCli(Socket s) throws Exception {
        cliList.put(s, new DataOutputStream(s.getOutputStream()));
    }

    public static synchronized void remCli(Socket s) throws Exception {
        cliList.get(s).write(0);
        cliList.remove(s);
        s.close();
    }

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

    class TCPChatSrvClient extends Thread {
        private Socket myS;
        private DataInputStream sIn;

        public TCPChatSrvClient(Socket s) {
            myS = s;
        }

        public void run() {
            int nChars;
            byte[] data = new byte[300];

            try {
                sIn = new DataInputStream(myS.getInputStream());
                while (true) {
                    nChars = sIn.read();
                    if (nChars == 0) break; // empty line means client wants to exit
                    System.out.println(sIn.read(data, 0, nChars));
                    AgvServer.sendToAll(nChars, data);
                    /*if(data == STATUS){
                        agv.changeStatus(Status.FREE);
                    }*/
                }
                // the client wants to exit
                AgvServer.remCli(myS);
            } catch (Exception ex) {
                System.out.println("Error");
            }


        }
    }
}
