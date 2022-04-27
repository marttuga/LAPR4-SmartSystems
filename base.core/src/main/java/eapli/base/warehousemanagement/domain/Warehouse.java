package eapli.base.warehousemanagement.domain;


import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;
import java.util.List;

@Entity
public class Warehouse implements AggregateRoot<WarehouseID> {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private WarehouseID WarehouseID;

    @Embedded
    private Plan plan;
    @Embedded
    private Aisle aisle;
    @Embedded
    private Row row;
    @Embedded
    private Shelf shelf;
    @Embedded
    private AGVDock agvDock;



    protected Warehouse(){

    }


    public Warehouse(eapli.base.warehousemanagement.domain.WarehouseID warehouseID, Plan plan,
                     Aisle aisle, Row row, Shelf shelf, AGVDock agvDock) {
        WarehouseID = warehouseID;
        this.plan = plan;
        this.aisle = aisle;
        this.row = row;
        this.shelf = shelf;
        this.agvDock = agvDock;
    }

    public Warehouse(Plan plan, Aisle aisle, Row row, AGVDock agvDock) {
        this.plan = plan;
        this.aisle = aisle;
        this.row = row;
        this.agvDock = agvDock;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public int compareTo(eapli.base.warehousemanagement.domain.WarehouseID other) {
        return AggregateRoot.super.compareTo(other);
    }

    @Override
    public eapli.base.warehousemanagement.domain.WarehouseID identity() {
        return null;
    }
}
