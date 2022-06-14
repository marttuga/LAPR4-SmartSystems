package eapli.base.warehousemanagement.application;

import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.Status;

public interface AGVChangeStatusController {

    AGV statusAGV(Status status);
}
