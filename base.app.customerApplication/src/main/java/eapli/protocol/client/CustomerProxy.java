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


import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.customer.domain.CustomerId;
import eapli.base.ordersmanagement.order.domain.OrderID;
import eapli.base.ordersmanagement.order.domain.ProductOrderDto;
import eapli.base.ordersmanagement.order.domain.Status;
import eapli.framework.time.util.Calendars;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class CustomerProxy {
    private static final Logger LOGGER = LogManager.getLogger(CustomerProxy.class);


    private static class ClientSocket {

        private Socket sock;
        private PrintWriter output;
        private BufferedReader input;

        /**
         * @param address
         * @param port
         *
         * @throws IOException
         */
        public void connect(final String address, final int port) throws IOException {
            InetAddress serverIP;

            serverIP = InetAddress.getByName(address);

            sock = new Socket(serverIP, port);
            output = new PrintWriter(sock.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            LOGGER.debug("Connected to {}", address);
        }

        /**
         * @param request
         */
        public void send(final String request) {
            output.println(request);
            LOGGER.debug("Sent message\n-----\n{}\n-----", request);
        }

        public List<String> recv() throws IOException {
            final var resp = new ArrayList<String>();

            var eof = false;
            do {
                final String inputLine = input.readLine();
                if (inputLine != null) {
                    if (inputLine.equals("")) {
                        eof = true;
                    } else {
                        resp.add(inputLine);
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
        public List<String> sendAndRecv(final String request) throws IOException {
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

    public List<ProductOrderDto> getOpenOrders(final Status id, final String c) throws IOException, FailedRequestException {
        final var socket = new ClientSocket();
        socket.connect(getAddress(), getPort());

        final String request = getOrders(id,c);
        final List<String> response = socket.sendAndRecv(request);

        socket.stop();

        return parseResponseMessageGetAvailableMeals(response);
    }

    private List<ProductOrderDto> parseResponseMessageGetAvailableMeals(final List<String> response)
            throws FailedRequestException {
        checkForErrorMessage(response);

        final List<ProductOrderDto> ret = new ArrayList<>();

        response.remove(0); // removes header
        response.forEach(s -> ret.add(parseResponseMessageLineGetAvailableMeals(s)));
        return ret;
    }

    private ProductOrderDto parseResponseMessageLineGetAvailableMeals(final String s) {
        final String[] tokens = s.split(",");
        return new ProductOrderDto(OrderID.valueOf(tokens[0]),
                Status.valueOf(tokens[2]));
    }

    public String getOrders(final Status id, final String c){
        return "OPEN_ORDERS, " + id + ","+ c;
    }



    private int getPort() {
        // TODO read from config file
        return 3333;
    }

    private String getAddress() {
        // TODO read from config file
        return "localhost";
    }


    private String removeDoubleQuotes(final String token) {
        return token.replace("\"", "").trim();
    }

    private void checkForErrorMessage(final List<String> response) throws FailedRequestException {
        final String[] tokens = response.get(0).split(",");
        final String messageType = tokens[0];

        if (messageType.equals("SERVER_ERROR")
                || messageType.equals("BAD_REQUEST")
                || messageType.equals("UNKNOWN_REQUEST")
                || messageType.equals("ERROR_IN_REQUEST")) {
            throw new FailedRequestException();
        }
    }
}
