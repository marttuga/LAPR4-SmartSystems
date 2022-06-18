package eapli.presentation;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class CustomerApp {
    static InetAddress serverIP;
    static SSLSocket sock;
    public static void main(String args[]) throws Exception {
        String nick, frase;
        byte[] data = new byte[300];

         final String KEYSTORE_PASS="password";



        if (args.length != 2) {
            System.out.println(args.length);
            System.out.println("Server IPv4/IPv6 address/DNS name is required as first argument");
            System.out.println("Client name is required as second argument (a matching keystore must exist)");
            System.exit(1);
        }

        // Trust these certificates provided by servers
        System.setProperty("orderCustomerSSL//orderServer.jks", args[1] + ".jks");
        System.setProperty("orderCustomerSSL//orderServer.pem", KEYSTORE_PASS);

        // Use this certificate and private key for client certificate when requested by the server
        System.setProperty("orderCustomerSSL//customerApp.jks", args[1] + ".jks");
        System.setProperty("orderCustomerSSL//customerApp.pem", KEYSTORE_PASS);

        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();

        try {
            serverIP = InetAddress.getByName(args[0]);
        } catch (UnknownHostException ex) {
            System.out.println("Invalid server specified: " + args[0]);
            System.exit(1);
        }


        try {
            sock = (SSLSocket) sf.createSocket(serverIP, 1112);
        } catch (IOException ex) {
            System.out.println("Failed to connect to: " + args[0] + ":" + 1112);
            System.out.println("Application aborted.");
            System.exit(1);
        }

        System.out.println("Connected to: " + args[0] + ":" + 1112);


        sock.startHandshake();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream sOut = new DataOutputStream(sock.getOutputStream());
        DataInputStream sIn = new DataInputStream(sock.getInputStream());


        System.out.println("Connected to server");
        System.out.print("Enter message to show connection: ");
        nick = in.readLine();


        // start a thread to read incoming messages from the server
        Thread serverConn = new Thread(new TcpChatCliConn(sock));
        serverConn.start();

        while (true) { // read messages from the console and send them to the server
            frase = in.readLine();
            if (frase.compareTo("exit") == 0) {
                sOut.write(0);
                break;
            }
            frase = "(" + nick + ") " + frase;
            data = frase.getBytes();
            sOut.write((byte) frase.length());
            sOut.write(data, 0, (byte) frase.length());
        }

        serverConn.join();
        sock.close();
    }
}


class TcpChatCliConn implements Runnable {
    private Socket s;
    private DataInputStream sIn;

    public TcpChatCliConn(Socket tcp_s) {
        s = tcp_s;
    }

    public void run() {
        int nChars;
        byte[] data = new byte[300];
        String frase;

        try {
            sIn = new DataInputStream(s.getInputStream());
            while (true) {
                nChars = sIn.read();
                if (nChars == 0) break;
                sIn.read(data, 0, nChars);
                frase = new String(data, 0, nChars);
                System.out.println(frase);
            }
        } catch (IOException ex) {
            System.out.println("Client disconnected.");
        }
    }
}