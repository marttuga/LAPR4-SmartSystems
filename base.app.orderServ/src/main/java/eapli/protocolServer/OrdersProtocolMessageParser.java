package eapli.protocolServer;


import eapli.base.ordersmanagement.order.application.CheckOpenOrderController;

import eapli.base.ordersmanagement.order.domain.Status;
import eapli.framework.csv.util.CsvLineMarshaler;
import eapli.framework.util.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;


@Utility
public class OrdersProtocolMessageParser {

    private static final Logger LOGGER = LogManager.getLogger(OrdersProtocolMessageParser.class);

    private static CheckOpenOrderController controller;

    public OrdersProtocolMessageParser(final CheckOpenOrderController controller) {
        this.controller = controller;
    }

    private CheckOpenOrderController getController() {
        return controller;
    }


    /**
     * Parse and build the request.
     *
     * @param inputLine
     * @return
     */

    public OrderProtocolRequest parse(final String inputLine) {
        // as a fallback make sure we return unknown
        OrderProtocolRequest request = new UnknownRequest(inputLine);

        // parse to determine which type of request and if it is sintactally valid
        String[] tokens;

        try {
            tokens = CsvLineMarshaler.tokenize(inputLine).toArray(new String[0]);
            if ("OPEN_ORDERS".equals(tokens[0])) {
                request = parseGetOrders(inputLine, tokens);
            }
        } catch (final ParseException e) {
            LOGGER.info("Unable to parse request: {}", inputLine);
            request = new BadRequest(inputLine, "Unable to parse request");
        }


        return request;
    }

    private OrderProtocolRequest parseGetOrders(final String inputLine, final String[] tokens) {
        OrderProtocolRequest request;
        if (tokens.length != 3) {
            request = new BadRequest(inputLine, "Wrong number of parameters");
        }
        else {
            request = new GetOpenOrdersRequest(getController(), inputLine, Status.valueOf(tokens[1]), CsvLineMarshaler.unquote(tokens[2]));
        }
        return request;
    }

}

