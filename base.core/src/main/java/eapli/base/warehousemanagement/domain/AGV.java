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

    @Enumerated(EnumType.STRING)
    private Status status;


    protected AGV(){

    }

    public AGV(Identifier identifier, int autonomy, AGVDock agvDock, String AGVDescription, Model model, MaxWeightCarry maxWeightCarry, Status status) {
        this.identifier = identifier;
        this.autonomy = autonomy;
        this.agvDock = agvDock;
        this.AGVDescription = AGVDescription;
        this.model = model;
        this.maxWeightCarry = maxWeightCarry;
        this.status = status;
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

    public Identifier getIdentifier() {
        return identifier;
    }

    public int getAutonomy() {
        return autonomy;
    }

    public AGVDock getAgvDock() {
        return agvDock;
    }

    public String getAGVDescription() {
        return AGVDescription;
    }

    public Model getModel() {
        return model;
    }

    public Status getStatus() {
        return status;
    }

    public MaxWeightCarry getMaxWeightCarry() {
        return maxWeightCarry;
    }
}
