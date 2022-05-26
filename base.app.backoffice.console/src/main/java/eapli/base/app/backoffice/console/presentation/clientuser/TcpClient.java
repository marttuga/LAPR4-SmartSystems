package eapli.base.app.backoffice.console.presentation.clientuser;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient {
    static InetAddress serverIP;
    static Socket sock;

    public static void main(String[] args) throws Exception {
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
        try {
            sock = new Socket(serverIP, 9999);
        } catch (IOException ex) {
            System.out.println("Erro ao establecer a ligação TCP");
            System.exit(1);
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream sOut = new DataOutputStream(sock.getOutputStream());
        DataInputStream sIn = new DataInputStream(sock.getInputStream());

    }
}
