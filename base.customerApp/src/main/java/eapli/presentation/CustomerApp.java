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
    public static void main(String[] args) throws Exception {
        String nick, frase;
        byte[] data = new byte[300];

         final String KEYSTORE_PASS="password";

        System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\marti\\Documents\\2ANO2SEMESTRE\\LAPR4\\LEI21_22_S4_2DK_01\\orderCustomerSSL\\customerApp.jks");
        System.setProperty("javax.net.ssl.trustStorePassword",KEYSTORE_PASS);

        System.setProperty("javax.net.ssl.keyStore","C:\\Users\\marti\\Documents\\2ANO2SEMESTRE\\LAPR4\\LEI21_22_S4_2DK_01\\orderCustomerSSL\\customerApp.jks");
        System.setProperty("javax.net.ssl.keyStorePassword",KEYSTORE_PASS);
        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();

        try {
            serverIP = InetAddress.getByName("localhost");
        } catch (UnknownHostException ex) {
            System.out.println("Invalid server specified: " + "localhost");
            System.exit(1);
        }


        try {
            sock = (SSLSocket) sf.createSocket(serverIP, 1112);
        } catch (IOException ex) {
            System.out.println("Failed to connect to: " +"localhost" + ":" + 1112);
            System.out.println("Application aborted.");
            System.exit(1);
        }

        System.out.println("Connected to: " + "localhost" + ":" + 1112);


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