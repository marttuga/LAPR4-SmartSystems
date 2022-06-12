package eapli.application;

import eapli.protocol.client.AGVDTO;
import eapli.protocol.client.DigitalTwinProxy;
import eapli.protocol.client.FailedRequestException;

import java.io.IOException;

public class RequestAgvController {

    static final DigitalTwinProxy proxy = new DigitalTwinProxy();

    public static AGVDTO getAgvs(String id) throws IOException, FailedRequestException {
        return proxy.getAgv(id);
    }
}
