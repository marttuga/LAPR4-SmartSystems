/*
 * Copyright (c) 2013-2022 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.protocol.client;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;


public class DigitalTwinProxy {
    private static final Logger LOGGER = LogManager.getLogger(DigitalTwinProxy.class);


    private static class ClientSocket {

        private SSLSocket sock;
        private PrintWriter output;
        private BufferedReader input;

        static final String KEYSTORE_PASS="keypass";


        /**
         * @param address
         * @param port
         *
         * @throws IOException
         */
        public void connect(final String address, final int port) throws IOException {
            InetAddress serverIP;

            serverIP = InetAddress.getByName(address);

            System.setProperty("javax.net.ssl.trustStore", "base.app.agvManagers/src/main/java/eapli/protocol/client/AgvManagerCli.jks");
            System.setProperty("javax.net.ssl.trustStorePassword",KEYSTORE_PASS);

            System.setProperty("javax.net.ssl.keyStore","base.app.agvManagers/src/main/java/eapli/protocol/client/AgvManagerCli.jks");
            System.setProperty("javax.net.ssl.keyStorePassword",KEYSTORE_PASS);

            SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
            try {
                sock = (SSLSocket) sf.createSocket(serverIP, port);
            } catch (IOException ex) {
                System.out.println("Failed to connect to: " + port);
                System.out.println("Application aborted.");
                System.exit(1);
            }
            LOGGER.debug("Connected to {}", address);
            sock.startHandshake();
            output = new PrintWriter(sock.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(sock.getInputStream()));

        }

        /**
         * @param request
         */
        public void send(final String request) {
            output.println(request);
            LOGGER.debug("Sent message\n-----\n{}\n-----", request);
        }

        public String recv() throws IOException {
            String resp = null;

            var eof = false;
            do {
                final String inputLine = input.readLine();
                System.out.println(inputLine);
                if (inputLine != null) {
                    if (inputLine.equals("")) {
                        eof = true;
                    } else {
                        resp=inputLine;
                    }
                }
            } while (!eof);

            LOGGER.debug("Received message:\n----\n{}\n----", resp);

            return resp;
        }

        /**
         * @param request
         *
         * @return
         *
         * @throws IOException
         */
        public String sendAndRecv(final String request) throws IOException {
            send(request);
            return recv();
        }

        /**
         * @throws IOException
         */
        public void stop() throws IOException {
            input.close();
            output.close();
            sock.close();
        }
    }

    public AGVDTO getAgv(final String id) throws IOException, FailedRequestException {
        final var socket = new ClientSocket();
        socket.connect(getAddress(), getPort());
        final String request = getAgvInfo(id);
        final String response = socket.sendAndRecv(request);
        socket.stop();
        return parseResponseMessageLineGetAGV(response);

    }

    private AGVDTO parseResponseMessageLineGetAGV(final String s) throws FailedRequestException {
        final String[] tokens = s.split(",");
        /*for (int i = 0; i < tokens.length; i++) {
            System.out.println(tokens[i]+ " ");
        }*/
        checkForErrorMessage(s);
        //return removeDoubleQuotes(tokens[1]);
        return new AGVDTO(Long.parseLong(tokens[0]), removeDoubleQuotes(tokens[1]),Integer.parseInt(tokens[3]),removeDoubleQuotes(tokens[2]),removeDoubleQuotes(tokens[4]), removeDoubleQuotes(tokens[5]));
    }

    public String getAgvInfo(String id){
        return "GET_AGV, \"" + id + "\"";
    }



    private int getPort() {
        // TODO read from config file
        return 2222;
    }

    private String getAddress() {
        // TODO read from config file
        return "localhost";
    }


    private String removeDoubleQuotes(final String token) {
        return token.replace("\"", "").trim();
    }

    private void checkForErrorMessage(final String response) throws FailedRequestException {
        final String[] tokens = response.split(",");
        final String messageType = tokens[0];

        if (messageType.equals("SERVER_ERROR")
                || messageType.equals("BAD_REQUEST")
                || messageType.equals("UNKNOWN_REQUEST")
                || messageType.equals("ERROR_IN_REQUEST")) {
            throw new FailedRequestException();
        }
    }
}
