package eapli;

import java.io.*;
import java.lang.reflect.Array;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;



class DigitalTwinCli {

    //static byte[] STATUS = new byte[] {1, 12, 0,0};

    static InetAddress serverIP;
    static Socket sock;


    public static void main(String args[]) throws Exception {

        try {
            sock = new Socket("localhost", 9999);
            System.out.println("Connected to server");

            DataOutputStream out = new DataOutputStream(sock.getOutputStream());
            DataInputStream in = new DataInputStream(sock.getInputStream());

            int option;
            do {
                System.out.println("Select a option:");
                System.out.println("1 - Change status AGV");
                System.out.println("2 - Exit");
                Scanner sc = new Scanner(System.in);
                option = sc.nextInt();

                if (option == 1) {
                    System.out.println("AGV Id: ");
                    int agvId = sc.nextInt();

                    //out.writeInt(option);
                    //out.writeInt(agvId);

                    System.out.println("Pedido solicitado ao Servidor! Aguarde um momento!");

                    int length = in.readInt();
                    byte[] data = new byte[length];
                    in.readFully(data);
                    String str = new String(data, StandardCharsets.UTF_8);
                    System.out.println("AGV number + " + agvId + "change status to FREE!");

                }

                if (option == 2) {
                    System.out.println("Cliente desconectado!");
                }

                if (option != 1 && option != 2) {
                    System.out.println("Escolha uma opção valida!");
                }
            } while (option != 2);
            sock.close();

        } catch (IOException ex) {
            System.out.println("AGV change status to FREE!");
            System.exit(1);
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
}


