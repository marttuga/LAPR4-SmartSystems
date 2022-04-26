package eapli.base.ordersmanagement.warehousemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;

@Entity
public class Warehouse implements AggregateRoot<WarehouseID> {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private WarehouseID WarehouseID;

    public Warehouse(){

    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public int compareTo(eapli.base.ordersmanagement.warehousemanagement.domain.WarehouseID other) {
        return AggregateRoot.super.compareTo(other);
    }

    @Override
    public eapli.base.ordersmanagement.warehousemanagement.domain.WarehouseID identity() {
        return null;
    }
}
