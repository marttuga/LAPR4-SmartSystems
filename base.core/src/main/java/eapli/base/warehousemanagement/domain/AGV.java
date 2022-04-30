package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;

@Entity
public class AGV implements AggregateRoot<Identifier> {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Identifier identifier;

    private int autonomy;

    @Embedded
    private AGVDock agvDock;

    private String AGVDescription;

    @Embedded
    private Model model;

    @Embedded
    private MaxWeightCarry maxWeightCarry;


    protected AGV(){

    }

    public AGV(Identifier identifier, int autonomy, AGVDock agvDock, String AGVDescription, Model model, MaxWeightCarry maxWeightCarry) {
        this.identifier = identifier;
        this.autonomy = autonomy;
        this.agvDock = agvDock;
        this.AGVDescription = AGVDescription;
        this.model = model;
        this.maxWeightCarry = maxWeightCarry;
    }

    public static int valueOfAutonomy(final int autonomy) {
        return (autonomy);
    }

    public static String valueOfDescription(final String description) {
        return description;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public int compareTo(Identifier other) {
        return AggregateRoot.super.compareTo(other);
    }

    @Override
    public Identifier identity() {
        return null;
    }
}
