package eapli.base.warehousemanagement.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;
import java.util.List;

@Entity
public class Warehouse implements AggregateRoot<WarehouseID> {

    @Column(insertable = false,updatable = false)
    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private WarehouseID WarehouseID;

    @Embedded
    @JsonProperty
    private Plan plan;

    @OneToMany
    private List<Aisle> aisle;

    @Embedded
    @JsonProperty
    private Row row;

    @Embedded
    private Shelf shelf;

    @OneToMany
    private List<AGVDock> agvDock;



    protected Warehouse(){

    }


    public Warehouse(eapli.base.warehousemanagement.domain.WarehouseID warehouseID, Plan plan,
                     List<Aisle> aisle, Row row, Shelf shelf, List<AGVDock> agvDock) {
        WarehouseID = warehouseID;
        this.plan = plan;
        this.aisle = aisle;
        this.row = row;
        this.shelf = shelf;
        this.agvDock = agvDock;
    }

    public Warehouse(Plan plan, List<Aisle> aisle, List<AGVDock> agvDock) {
        this.plan = plan;
        this.aisle = aisle;
        this.agvDock = agvDock;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }


    @Override
    public eapli.base.warehousemanagement.domain.WarehouseID identity() {
        return null;
    }

    public eapli.base.warehousemanagement.domain.WarehouseID getWarehouseID() {
        return WarehouseID;
    }

    public Plan getPlan() {
        return plan;
    }

    public List<Aisle> getAisle() {
        return aisle;
    }

    public Row getRow() {
        return row;
    }

    public Shelf getShelf() {
        return shelf;
    }

    public List<AGVDock> getAgvDock() {
        return agvDock;
    }
}
