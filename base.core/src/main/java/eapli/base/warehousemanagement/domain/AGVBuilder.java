package eapli.base.warehousemanagement.domain;

import eapli.framework.application.ApplicationService;

@ApplicationService
public class AGVBuilder {

    private Identifier identifier;

    private int autonomy;

    private String AGVDescription;

    private Model model;

    private MaxWeightCarry maxWeightCarry;

    private AGVDock agvDock;

    public AGVBuilder withIdentifier(final Identifier identifier) {
        this.identifier = identifier;
        return this;
    }

    public AGVBuilder withAutonomy(final int autonomy) {
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

    public AGV build(){
        return new AGV(identifier,autonomy,agvDock,AGVDescription,model,maxWeightCarry);
    }
}
