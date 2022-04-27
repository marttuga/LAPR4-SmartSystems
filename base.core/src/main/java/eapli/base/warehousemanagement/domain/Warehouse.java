package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;

@Entity
public class Warehouse implements AggregateRoot<WarehouseID> {

    @Column(insertable = false,updatable = false)
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
    private AGVDock AGVDock;


    public Warehouse(){

    }

    public Warehouse(eapli.base.warehousemanagement.domain.WarehouseID warehouseID,
                     Plan plan, Aisle aisle, Row row, Shelf shelf,
                     eapli.base.warehousemanagement.domain.AGVDock AGVDock) {
        WarehouseID = warehouseID;
        this.plan = plan;
        this.aisle = aisle;
        this.row = row;
        this.shelf = shelf;
        this.AGVDock = AGVDock;
    }

    public Warehouse(Plan plan, Aisle aisle, Row row) {
        this.plan = plan;
        this.aisle = aisle;
        this.row = row;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }


    @Override
    public eapli.base.warehousemanagement.domain.WarehouseID identity() {
        return null;
    }
}
