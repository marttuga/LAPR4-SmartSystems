package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class AGV implements AggregateRoot<Identifier> {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Identifier identifier;

    private int autonomy;

    private String AGVDescription;

    private Model model;

    private MaxWeightCarry maxWeightCarry;

    private Task task;


    protected AGV(){

    }

    public AGV(Identifier identifier, int autonomy, String AGVDescription, Model model, MaxWeightCarry maxWeightCarry, Task task) {
        this.identifier = identifier;
        this.autonomy = autonomy;
        this.AGVDescription = AGVDescription;
        this.model = model;
        this.maxWeightCarry = maxWeightCarry;
        this.task = task;
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
