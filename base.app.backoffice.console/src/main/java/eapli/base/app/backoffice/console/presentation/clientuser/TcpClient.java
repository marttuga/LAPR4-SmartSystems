package eapli.base.app.backoffice.console.presentation.clientuser;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

public class TcpClient {
    static InetAddress serverIP;
    static Socket sock;

    public static void main(String[] args) {

        /*
        if (args.length != 1) {
            System.out.println("Server IPv4/IPv6 address ou DNS é requerido");
            System.exit(1);
        }
        try {
            serverIP = InetAddress.getByName(args[0]);
        } catch (UnknownHostException ex) {
            System.out.println("Server invalido: " + args[0]);
            System.exit(1);
        }
         */

        try {
            sock = new Socket("localhost", 9999);
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
            System.out.println("Estabeleceu a ligação TCP");
            System.exit(1);
        }

    }
}
