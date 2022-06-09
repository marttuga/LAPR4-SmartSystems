package eapli.base.warehousemanagement.domain;

import eapli.base.ordersmanagement.order.domain.LineOrder;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;

@Entity
public class AGV implements AggregateRoot<Identifier> {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Identifier identifier;

    @Embedded
    private Autonomy autonomy;

    @Embedded
    private AGVDock agvDock;

    private String AGVDescription;

    @Embedded
    private Model model;

    @Embedded
    private MaxWeightCarry maxWeightCarry;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ProductOrder order;

    public AGV(){

    }

    public AGV(Identifier identifier, Autonomy autonomy, AGVDock agvDock, String AGVDescription, Model model, MaxWeightCarry maxWeightCarry, Status status) {
        this.identifier = identifier;
        this.autonomy = autonomy;
        this.agvDock = agvDock;
        this.AGVDescription = AGVDescription;
        this.model = model;
        this.maxWeightCarry = maxWeightCarry;
        this.status = status;
    }

    public AGV(Identifier identifier, Autonomy autonomy, AGVDock agvDock, String AGVDescription, Model model, MaxWeightCarry maxWeightCarry, Status status, ProductOrder order) {
        this.identifier = identifier;
        this.autonomy = autonomy;
        this.agvDock = agvDock;
        this.AGVDescription = AGVDescription;
        this.model = model;
        this.maxWeightCarry = maxWeightCarry;
        this.status = status;
        this.order = order;
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

    public Autonomy getAutonomy(){return autonomy;}

    public void changeStatus(Status status) {
        this.status = status;
    }

    public void changeOrder(ProductOrder order) {
        this.order = order;
    }

    public ProductOrder getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "AGV:" + identifier +
                "\nDock=" + agvDock +
                "\nDescription='" + AGVDescription + '\'' +
                "\nModel=" + model +
                "\nStatus=" + status ;
    }
}
