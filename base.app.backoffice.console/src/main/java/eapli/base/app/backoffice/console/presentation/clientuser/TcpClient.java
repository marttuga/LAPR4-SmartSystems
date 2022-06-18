package eapli.base.app.backoffice.console.presentation.clientuser;

import eapli.CodingAndDecoding;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.base.warehousemanagement.domain.Status;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

public class TcpClient {
    static final int SERVER_PORT=9999;
    static final String KEYSTORE_PASS="forgotten";

    static InetAddress serverIP;
    static SSLSocket sock;

    public static void main(String[] args) {

        System.out.println("primeiro argumento" + args[0]);
        /*
        if(args.length!=2) {
            System.out.println("Server IPv4/IPv6 address/DNS name is required as first argument");
            System.out.println("Client name is required as second argument (a matching keystore must exist)");
            System.exit(1); }

         */

        // Trust these certificates provided by servers
        System.setProperty("javax.net.ssl.trustStore", args[0]+".jks");
        System.setProperty("javax.net.ssl.trustStorePassword",KEYSTORE_PASS);

        // Use this certificate and private key for client certificate when requested by the server
        System.setProperty("javax.net.ssl.keyStore",args[0]+".jks");
        System.setProperty("javax.net.ssl.keyStorePassword",KEYSTORE_PASS);

        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();

        try {
            serverIP = InetAddress.getByName(args[0]);
        } catch(UnknownHostException ex) {
            System.out.println("Invalid server specified: " + args[0]);
            System.exit(1); }


        try {
            sock = (SSLSocket) sf.createSocket(serverIP,SERVER_PORT);
            System.out.println("Connected to server");

            DataOutputStream out = new DataOutputStream(sock.getOutputStream());
            DataInputStream in = new DataInputStream(sock.getInputStream());

            int option;
            do {
                System.out.println("Select a option:");
                System.out.println("1 - Designar Order a um AGV");
                System.out.println("2 - Exit");
                Scanner sc = new Scanner(System.in);
                option = sc.nextInt();

                if (option == 1) {
                    System.out.println("Order Id: ");
                    int orderId = sc.nextInt();

                    out.writeInt(option);
                    out.writeInt(orderId);

                    System.out.println("Pedido solicitado ao Servidor! Aguarde um momento!");

                    int length = in.readInt();
                    byte[] data = new byte[length];
                    in.readFully(data);
                    String str = new String(data, StandardCharsets.UTF_8);
                    System.out.println("Order number + " + orderId + "assigned to AGV number " + str + " with success!");

                }

                if(option == 2) {
                    System.out.println("Cliente desconectado!");
                }

                if (option != 1 && option != 2) {
                    System.out.println("Escolha uma opção valida!");
                }
            } while (option != 2);
            sock.close();

        } catch (IOException ex) {
            System.out.println("Failed to connect to: " + args[0] + ":" + SERVER_PORT);
            System.out.println("Application aborted.");
            System.exit(1);
        }

    }
}
