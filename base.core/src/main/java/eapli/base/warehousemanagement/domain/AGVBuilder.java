package eapli.base.warehousemanagement.domain;

import eapli.framework.application.ApplicationService;

@ApplicationService
public class AGVBuilder {

    private Identifier identifier;

    private int autonomy;

    private String AGVDescription;

    private Model model;

    private MaxWeightCarry maxWeightCarry;

    private Task task;

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
        this.identifier = identifier;
        return this;
    }

    public AGVBuilder withMaxWeightCarry(final MaxWeightCarry maxWeightCarry) {
        this.maxWeightCarry = maxWeightCarry;
        return this;
    }

    public AGVBuilder withTask(final Task task) {
        this.task = task;
        return this;
    }


    public AGV build(){
        return new AGV(identifier,autonomy,AGVDescription,model,maxWeightCarry,task);
    }
}
