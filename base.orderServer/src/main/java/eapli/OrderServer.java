package eapli;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class OrderServer {

        private static HashMap<Socket, DataOutputStream> cliList = new HashMap<>();

        public static synchronized void sendToAll(int len, byte[] data) throws Exception {
            for (DataOutputStream cOut : cliList.values()) {
                System.out.println("Client Connected");
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


        private static ServerSocket sock;

        public static void main(String args[]) throws Exception {
            int i;

            try {
                sock = new ServerSocket(1111);
            } catch (IOException ex) {
                System.out.println("Local port number not available.");
                System.exit(1);
            }


            while (true) {
                Socket s = sock.accept(); // wait for a new client connection request
                addCli(s);
                Thread cli = new OrderServerClient(s);
                cli.start();
            }
        }
    }
 class OrderServerClient extends Thread {
    private Socket myS;
    private DataInputStream sIn;

    public OrderServerClient(Socket s) { myS=s;}

    public void run() {
        int nChars;
        byte[] data = new byte[300];

        try {
            sIn = new DataInputStream(myS.getInputStream());
            while(true) {
                nChars=sIn.read();
                if(nChars ==0) break; // empty line means client wants to exit
                System.out.printf("num chars: %d",sIn.read(data,0,nChars));
                System.out.println();
                System.out.println(new String(data, StandardCharsets.UTF_8));
                OrderServer.sendToAll(nChars,data);
            }


            // the client wants to exit
            OrderServer.remCli(myS);
        }
        catch(Exception ex) { System.out.println("Error"); }
    }
}



