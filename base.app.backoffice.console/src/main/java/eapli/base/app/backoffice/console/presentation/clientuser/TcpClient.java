package eapli.base.app.backoffice.console.presentation.clientuser;

import eapli.CodingAndDecoding;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.base.warehousemanagement.domain.Status;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;

public class TcpClient {
    static InetAddress serverIP;
    static Socket sock;
    private static CodingAndDecoding cod = new CodingAndDecoding();

    public static void main(String[] args) throws Exception {

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

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream sOut = new DataOutputStream(sock.getOutputStream());

            System.out.println("Connected to server");

            // start a thread to read incoming messages from the server
            Thread serverConn = new Thread(new TcpClientConn(sock));
            serverConn.start();


            while (true) {
                System.out.println("Select a option:");
                System.out.println("1 - Designar Order a um AGV");
                System.out.println("2 - Update AGV Status");
                System.out.println("3 - Exit");
                String input = in.readLine();

                if (Objects.equals(input, "1")) {
                    System.out.print("Order id:");
                    String orderId = in.readLine();
                    cod.Coding_String(1,1, orderId);
                }

                if (Objects.equals(input, "2")) {
                    System.out.println("AGV ID:");
                    String agvId = in.readLine();
                    System.out.println("Choose AGV Status to update:");
                    System.out.println("1 - Free");
                    System.out.println("2 - Occupied");
                    System.out.println("3 - Charging");
                    String status = in.readLine();
                    if (status.equals("1")) {
                        byte[] codedMessage = cod.Coding_String(1,1, agvId + Status.FREE);
                        sOut.write(codedMessage);
                    } else if (status.equals("2")) {
                        byte[] codedMessage = cod.Coding_String(1,1, agvId + Status.OCCUPIED);
                        sOut.write(codedMessage);
                    } else if (status.equals("3")) {
                        byte[] codedMessage = cod.Coding_String(1,1, agvId + Status.CHARGING);
                        sOut.write(codedMessage);
                    } else {
                        System.out.println("Select one valid option!");
                    }
                }

                if(Objects.equals(input, "3")) {
                    System.out.println("Cliente desconectado!");
                    sOut.write(0);
                    break;
                }
            }

        } catch (IOException ex) {
            System.out.println("Erro ao establecer a ligação TCP");
            System.exit(1);
        }

        sock.close();
    }
}

class TcpClientConn implements Runnable {
    private Socket s;
    private DataInputStream sIn;

    public TcpClientConn(Socket tcp_s) {
        s = tcp_s;
    }

    public void run() {
        int nChars;
        byte[] data = new byte[300];
        String frase;

        try {
            sIn = new DataInputStream(s.getInputStream());
            while(true) {
                nChars=sIn.read();
                if(nChars==0) break;
                sIn.read(data,0,nChars);
                frase = new String(data, 0, nChars);
                System.out.println(frase);
            }
        }
        catch(IOException ex) {
            System.out.println("Client disconnected.");
        }
    }
}
