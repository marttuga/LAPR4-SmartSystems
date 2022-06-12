package eapli.base.app.agvDigitalTwin.protocol.server;


import eapli.base.warehousemanagement.application.AgvRequestController;
import eapli.framework.csv.util.CsvLineMarshaler;
import eapli.framework.util.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;


@Utility
public class DigitalTwinProtocolMessageParser {

    private static final Logger LOGGER = LogManager.getLogger(DigitalTwinProtocolMessageParser.class);

    private static AgvRequestController controller;

    public DigitalTwinProtocolMessageParser(final AgvRequestController controller) {
        this.controller = controller;
    }

    private AgvRequestController getController() {
        return controller;
    }


    /**
     * Parse and build the request.
     *
     * @param inputLine
     * @return
     */

    public AGVTwinProtocolRequest parse(final String inputLine) {
        // as a fallback make sure we return unknown
        AGVTwinProtocolRequest request = new UnknownRequest(inputLine);

        // parse to determine which type of request and if it is sintactally valid
        String[] tokens;

        try {
            tokens = CsvLineMarshaler.tokenize(inputLine).toArray(new String[0]);
            if ("GET_AGV".equals(tokens[0])) {
                request = parseGetAGV(inputLine, tokens);
            }
        } catch (final ParseException e) {
            LOGGER.info("Unable to parse request: {}", inputLine);
            request = new BadRequest(inputLine, "Unable to parse request");
        }


        return request;
    }

    private AGVTwinProtocolRequest parseGetAGV(final String inputLine, final String[] tokens) {
        AGVTwinProtocolRequest request;
        if (tokens.length != 2) {
            request = new BadRequest(inputLine, "Wrong number of parameters");
        }
        else {
            request = new GetAGVRequest(getController(), inputLine, CsvLineMarshaler.unquote(tokens[1]));
        }
        return request;
    }

}

