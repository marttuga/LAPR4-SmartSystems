package eapli.base.app.agvDigitalTwin.presentation;
import eapli.base.app.agvDigitalTwin.protocol.server.AGVTwinProtocolRequest;
import eapli.base.app.agvDigitalTwin.protocol.server.DigitalTwinProtocolMessageParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class DigitalTwinProtocolServer {

    private static final Logger LOGGER = LogManager.getLogger(DigitalTwinProtocolServer.class);

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private final DigitalTwinProtocolMessageParser parser;

        static final String TRUSTED_STORE = "base.app.agvDigitalTwin/src/main/java/eapli/base/app/agvDigitalTwin/protocol/server/AgvTwinSrv.jks";


        static final String KEYSTORE_PASS="keypass";



        public ClientHandler(final Socket socket, final DigitalTwinProtocolMessageParser parser) {
            this.clientSocket = socket;
            this.parser = parser;
        }

        @Override
        public void run() {
            System.setProperty("javax.net.ssl.trustStore", TRUSTED_STORE);
            System.setProperty("javax.net.ssl.trustStorePassword",KEYSTORE_PASS);

            System.setProperty("javax.net.ssl.keyStore",TRUSTED_STORE);
            System.setProperty("javax.net.ssl.keyStorePassword",KEYSTORE_PASS);

            final var clientIP = clientSocket.getInetAddress();
            LOGGER.debug("Acepted connection from {}:{}", clientIP.getHostAddress(), clientSocket.getPort());


            try (var out = new PrintWriter(clientSocket.getOutputStream(), true);
                 var in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    LOGGER.debug("Received message:----\n{}\n----", inputLine);
                    final AGVTwinProtocolRequest request = parser.parse(inputLine);
                    final String response = request.execute();
                    out.println(response);
                    LOGGER.debug("Sent message:----\n{}\n----", response);
                    if (request.isGoodbye()) {
                        break;
                    }
                }
            } catch (final IOException e) {
                LOGGER.error(e);
            } finally {
                try {
                    clientSocket.close();
                    LOGGER.debug("Closing client socket {}:{}", clientIP.getHostAddress(), clientSocket.getPort());
                } catch (final IOException e) {
                    LOGGER.error("While closing the client socket {}:{}", clientIP.getHostAddress(),
                            clientSocket.getPort(), e);
                }
                // null the reference to ensure it will be caught by the garbage collector
                clientSocket = null;

                // helper debug - SHOULD NOT BE USED IN PRODUCTION CODE!!!
                if (LOGGER.isDebugEnabled()) {
                    final int finalThreadCount = Thread.activeCount();
                    LOGGER.debug("Ending client thread - final thread count: {}", finalThreadCount);
                    final Thread[] t = new Thread[finalThreadCount];
                    final int n = Thread.enumerate(t);
                    for (var i = 0; i < n; i++) {
                        LOGGER.debug("T {}: {}", t[i].getId(), t[i].getName());
                    }
                }
            }
        }
    }

    private final DigitalTwinProtocolMessageParser parser;

    public DigitalTwinProtocolServer(final DigitalTwinProtocolMessageParser parser){
        this.parser = parser;
    }


    private void listen(final int port) throws IOException {
        SSLServerSocket socket = null;
        SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        try {
            socket =  (SSLServerSocket) sslF.createServerSocket(port);
            socket.setNeedClientAuth(true);

        } catch (final IOException e) {
            LOGGER.error(e);
        }
        while (true) {
            final var clientSocket = socket.accept();
            new ClientHandler(clientSocket, parser).start();
        }
    }

    /**
     * Wait for connections.
     *
     * @param port
     * @param blocking
     *            if {@code false} the socket runs in its own thread and does not block calling
     *            thread.
     */

    public void start(final int port, final boolean blocking) throws IOException {
        if (blocking) {

            listen(port);
        } else {
            new Thread(() -> {
                try {
                    listen(port);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}

