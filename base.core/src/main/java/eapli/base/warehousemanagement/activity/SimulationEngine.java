package eapli.base.warehousemanagement.activity;

import eapli.base.warehousemanagement.domain.*;
import eapli.framework.domain.model.ValueObject;

import javax.persistence.OneToOne;

public class SimulationEngine implements ValueObject {

    @OneToOne
    private Plan plan;
    private Positioning positioning;
    private Autonomy autonomy;
    private AGVDock agvDock;
    private Model model;
    private Speed speed;


    public SimulationEngine(Plan plan, Positioning positioning, Autonomy autonomy, AGVDock agvDock, Model model, Speed speed) {
        this.plan = plan;
        this.positioning = positioning;
        this.autonomy = autonomy;
        this.agvDock = agvDock;
        this.model = model;
        this.speed = speed;
    }


}
