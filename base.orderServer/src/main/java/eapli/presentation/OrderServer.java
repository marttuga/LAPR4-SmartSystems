package eapli.presentation;


import eapli.application.OrderServerController;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;


public class OrderServer {

    static final String TRUSTED_STORE = "orderServer.jks";
    static final String KEYSTORE_PASS = "password";
    //private static final OrderServerController orderServerController = new OrderServerController();

    private static HashMap<Socket, DataOutputStream> cliList = new HashMap<>();

    public static synchronized void sendToAll(int len, byte[] data) throws Exception {
        //System.out.println("Client Connected");
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
        int i;
        SSLServerSocket sock = null;
        Socket cliSock;
        // Trust these certificates provided by authorized clients
        System.setProperty("orderCustomerSSL//customerApp.jks", TRUSTED_STORE);
        System.setProperty("orderCustomerSSL//customerApp.pem", KEYSTORE_PASS);

        // Use this certificate and private key as server certificate
        System.setProperty("orderCustomerSSL//orderServer.jks", TRUSTED_STORE);
        System.setProperty("orderCustomerSSL//orderServer.pem", KEYSTORE_PASS);

        SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        try {
            sock = (SSLServerSocket) sslF.createServerSocket(1112);
            sock.setNeedClientAuth(true);
        } catch (IOException ex) {
            System.out.println("Server failed to open local port " + 1112);
            System.exit(1);
        }

        while(true) {
            cliSock=sock.accept();
            addCli(cliSock);
            Thread cli = new OrderServerClient(cliSock);
            cli.start();
        }
    }
}

class OrderServerClient extends Thread {
    private static final OrderServerController orderServerController = new OrderServerController();
    private Socket myS;
    private DataInputStream sIn;

    public OrderServerClient(Socket s) {
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
                System.out.printf("num chars: %d", sIn.read(data, 0, nChars));
                System.out.println();
                System.out.println(new String(data, StandardCharsets.UTF_8));
               OrderServer.sendToAll(nChars, data);
            }


            // the client wants to exit
            OrderServer.remCli(myS);
        } catch (Exception ex) {
            System.out.println("Error");
        }
    }
}