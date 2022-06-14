package eapli.base.app.agvDigitalTwin.application;


import eapli.base.app.agvDigitalTwin.protocol.client.AGVDTO;
import eapli.base.app.agvDigitalTwin.protocol.client.AgvManagerProxy;
import eapli.base.app.agvDigitalTwin.protocol.client.FailedRequestException;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.Status;

import java.io.IOException;

public class ChangeStatusAgvController {

    static final AgvManagerProxy proxy = new AgvManagerProxy();
    static final AGV agv = new AGV();

    public static AGVDTO getAgvs(Status status) throws IOException, FailedRequestException {
        agv.changeStatus(status);
        return proxy.getAgv(status);
    }


}
