package eapli.base.app.agvDigitalTwin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eapli.base.app.agvDigitalTwin.presentation.DigitalTwinProtocolServer;
import eapli.base.app.agvDigitalTwin.protocol.server.DigitalTwinProtocolMessageParser;
import eapli.base.warehousemanagement.application.AgvRequestControllerImpl;


public class AgvTwinSrv {

    // TODO read port number from property file
    private static final int CONNECTION_PORT = 2222;
    private static final Logger LOGGER = LogManager.getLogger(AgvTwinSrv.class);



    /**
     * Avoid instantiation of this class.
     */
    private AgvTwinSrv() {
    }

    public static void main(final String[] args) {
        LOGGER.info("Starting Connection");

/*        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new BasePasswordPolicy(),
                new PlainTextEncoder());*/

        LOGGER.info("Starting the server socket on port {}", CONNECTION_PORT);
        final var server = new DigitalTwinProtocolServer(buildServerDependencies());
        server.start(CONNECTION_PORT, true);

        LOGGER.info("Exiting connection");
        System.exit(0);
    }

    private static DigitalTwinProtocolMessageParser buildServerDependencies() {
        return new DigitalTwinProtocolMessageParser(new AgvRequestControllerImpl());
    }

}


