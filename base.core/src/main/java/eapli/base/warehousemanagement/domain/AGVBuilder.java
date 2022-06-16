package eapli.base.warehousemanagement.domain;

import eapli.framework.application.ApplicationService;

import javax.persistence.Transient;
import java.util.List;

@ApplicationService
public class AGVBuilder {

    private Identifier identifier;

    private Autonomy autonomy;

    private String AGVDescription;

    private Model model;

    private Position position;

    private List<Sensor> sensorList;

    private MaxWeightCarry maxWeightCarry;

    private AGVDock agvDock;

    private Status status;

    public AGVBuilder withIdentifier(final Identifier identifier) {
        this.identifier = identifier;
        return this;
    }

    public AGVBuilder withAutonomy(final Autonomy autonomy) {
        this.autonomy = autonomy;
        return this;
    }

    public AGVBuilder withAGVDescription(final String AGVDescription) {
        this.AGVDescription = AGVDescription;
        return this;
    }

    public AGVBuilder withModel(final Model model) {
        this.model = model;
        return this;
    }

    public AGVBuilder withMaxWeightCarry(final MaxWeightCarry maxWeightCarry) {
        this.maxWeightCarry = maxWeightCarry;
        return this;
    }

    public AGVBuilder withAGVDock(final AGVDock agvDock) {
        this.agvDock = agvDock;
        return this;
    }

    public AGVBuilder withStatus(final Status status) {
        this.status = status;
        return this;
    }

    public AGVBuilder withPosition(final Position position) {
        this.position = position;
        return this;
    }

    public AGVBuilder withSensors(final List<Sensor> sensorList) {
        this.sensorList = sensorList;
        return this;
    }

    public AGV build(){
        return new AGV(identifier, autonomy, agvDock, AGVDescription, model, position, sensorList, maxWeightCarry,status);
    }
}
