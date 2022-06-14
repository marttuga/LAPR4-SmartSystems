package eapli.presentation;


import eapli.application.OrderServerController;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;


public class OrderServer {


    private static ServerSocket sock;
    private static final OrderServerController orderServerController = new OrderServerController();
    public static void main(String args[]) throws Exception {
        int i;

        try {
            sock = new ServerSocket(1111);
        } catch (IOException ex) {
            System.out.println("Local port number not available.");
            System.exit(1);
        }


        while (true) {
            Socket s = sock.accept(); // wait for a new client connection request
            OrderServerController.addCli(s);
            Thread cli = new OrderServerClient(s);
            cli.start();
        }
    }



}
class OrderServerClient extends Thread {
    private static final OrderServerController orderServerController = new OrderServerController();
    private Socket myS;
    private DataInputStream sIn;

    public OrderServerClient(Socket s) { myS=s;}

    public void run() {
        int nChars;
        byte[] data = new byte[300];

        try {
            sIn = new DataInputStream(myS.getInputStream());
            while(true) {
                nChars=sIn.read();
                if(nChars ==0) break; // empty line means client wants to exit
                System.out.printf("num chars: %d",sIn.read(data,0,nChars));
                System.out.println();
                System.out.println(new String(data, StandardCharsets.UTF_8));
                OrderServerController.sendToAll(nChars,data);
            }


            // the client wants to exit
            OrderServerController.remCli(myS);
        }
        catch(Exception ex) { System.out.println("Error"); }
    }
}