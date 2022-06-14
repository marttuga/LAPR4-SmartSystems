package eapli.server;

import eapli.presentation.OrdersProtocolServer;
import eapli.protocolServer.OrdersProtocolMessageParser;
import eapli.base.ordersmanagement.order.application.CheckOpenOrderController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class OrdersSrv {

    // TODO read port number from property file
    private static final int CONNECTION_PORT = 3333;
    private static final Logger LOGGER = LogManager.getLogger(OrdersSrv.class);



    /**
     * Avoid instantiation of this class.
     */
    private OrdersSrv() {
    }

    public static void main(final String[] args) {
        LOGGER.info("Starting Connection");

/*        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new BasePasswordPolicy(),
                new PlainTextEncoder());*/

        LOGGER.info("Starting the server socket on port {}", CONNECTION_PORT);
        final var server = new OrdersProtocolServer(buildServerDependencies());
        server.start(CONNECTION_PORT, true);

        LOGGER.info("Exiting connection");
        System.exit(0);
    }

    private static OrdersProtocolMessageParser buildServerDependencies() {
        return new OrdersProtocolMessageParser(new CheckOpenOrderController());
    }

}


