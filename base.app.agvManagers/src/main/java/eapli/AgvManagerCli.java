package eapli;

import java.io.*;
import java.net.*;

class AgvManagerCli {


    static InetAddress serverIP;
    static Socket sock;


    public static void main(String args[]) throws Exception {
        String nick, frase;
        byte[] data = new byte[300];

        try {
            //Cria o socket com o port 2222
            sock = new Socket("localhost", 2222);
        } catch (IOException ex) {
            System.out.println("Failed to connect.");
            System.exit(1);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream sOut = new DataOutputStream(sock.getOutputStream());

        System.out.println("Connected to server");
        System.out.print("Enter your nickname: ");
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

