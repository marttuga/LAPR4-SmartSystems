package eapli.base.warehousemanagement.application;

//import eapli.client.DigitalTwinProxy;
import eapli.base.ordersmanagement.CustomerCliOrderServer.FailedRequestException;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.Identifier;

import java.io.IOException;

public interface AgvRequestController {

    AGV findAgv(String id);


}
