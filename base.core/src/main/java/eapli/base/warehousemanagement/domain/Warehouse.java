package eapli.base.warehousemanagement.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;

@Entity
public class Warehouse implements AggregateRoot<WarehouseID> {

    @Column(insertable = false,updatable = false)
    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private WarehouseID WarehouseID;

    @Transient
    @Embedded
    @JsonProperty
    private Plan plan;
    @Transient
    @Embedded
    @JsonProperty
    private Aisle aisle;
    @Transient
    @Embedded
    @JsonProperty
    private Row row;
    @Embedded
    private Shelf shelf;
    @Transient
    @Embedded
    @JsonProperty
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

    public Warehouse(Plan plan, Aisle aisle, AGVDock agvDock) {
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
}
