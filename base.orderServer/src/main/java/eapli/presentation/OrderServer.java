package eapli.presentation;


import eapli.application.OrderServerController;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;


public class OrderServer {

    static final String TRUSTED_STORE = "server_J.jks";
    static final String KEYSTORE_PASS = "forgotten";
    private static final OrderServerController orderServerController = new OrderServerController();

    public static void main(String args[]) throws Exception {
        int i;
        SSLServerSocket sock = null;
        Socket cliSock;
        // Trust these certificates provided by authorized clients
        System.setProperty("javax.net.ssl.trustStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.trustStorePassword", KEYSTORE_PASS);

        // Use this certificate and private key as server certificate
        System.setProperty("javax.net.ssl.keyStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASS);

        SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        try {
            sock = (SSLServerSocket) sslF.createServerSocket(1111);
            sock.setNeedClientAuth(true);
        } catch (IOException ex) {
            System.out.println("Server failed to open local port " + 1111);
            System.exit(1);
        }

        while(true) {
            cliSock=sock.accept();
            OrderServerController.addCli(cliSock);
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
                OrderServerController.sendToAll(nChars, data);
            }


            // the client wants to exit
            OrderServerController.remCli(myS);
        } catch (Exception ex) {
            System.out.println("Error");
        }
    }
}