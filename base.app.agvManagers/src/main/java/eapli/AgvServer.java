package eapli;


import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class AgvServer {

    private static HashMap<Socket, DataOutputStream> cliList = new HashMap<>();

    public static synchronized void sendToAll(int len, byte[] data) throws Exception {
        for (DataOutputStream cOut : cliList.values()) {
            cOut.write(len);
            cOut.write(data, 0, len);
        }

    }

    public static synchronized void addCli(Socket s) throws Exception {
        cliList.put(s, new DataOutputStream(s.getOutputStream()));
    }

    public static synchronized void remCli(Socket s) throws Exception {
        cliList.get(s).write(0);
        cliList.remove(s);
        s.close();
    }

    static final int SERVER_PORT=9999;
    static final String TRUSTED_STORE = "server.jks";                       //criar a key store
    static final String KEYSTORE_PASS="forgotten";                          //password

    public static void main(String args[]) throws Exception {

        SSLServerSocket sock = null;
        Socket cliSock;

        // Trust these certificates provided by authorized clients
        System.setProperty("javax.net.ssl.trustStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.trustStorePassword",KEYSTORE_PASS);

        // Use this certificate and private key as server certificate
        System.setProperty("javax.net.ssl.keyStore",TRUSTED_STORE);
        System.setProperty("javax.net.ssl.keyStorePassword",KEYSTORE_PASS);

        SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault(); //factory for creating secure sockets

        try {
            sock = (SSLServerSocket) sslF.createServerSocket(SERVER_PORT);
            //sock.setNeedClientAuth(true);

        } catch (IOException ex) {
            System.out.println("Server failed to open local port " + SERVER_PORT);
            System.exit(1);
        }

        while (true) {
            cliSock=sock.accept();
            //Thread cli = new TCPChatSrvClient(s);
            //cli.start();
            new Thread(new AgvServerThread(cliSock)).start();
        }
    }
}

class TCPChatSrvClient extends Thread {
        private Socket myS;
        private DataInputStream sIn;

        public TCPChatSrvClient(Socket s) {
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
                    System.out.println(sIn.read(data, 0, nChars));
                    AgvServer.sendToAll(nChars, data);
                    /*if(data == STATUS){
                        agv.changeStatus(Status.FREE);
                    }*/
                }
                // the client wants to exit
                AgvServer.remCli(myS);
            } catch (Exception ex) {
                System.out.println("Error");
            }


        }
    }

