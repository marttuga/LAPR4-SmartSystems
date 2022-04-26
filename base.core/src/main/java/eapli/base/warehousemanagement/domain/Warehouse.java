package eapli.base.warehousemanagement.domain;

import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.product.domain.Product;
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
    public int compareTo(eapli.base.warehousemanagement.domain.WarehouseID other) {
        return AggregateRoot.super.compareTo(other);
    }

    @Override
    public eapli.base.warehousemanagement.domain.WarehouseID identity() {
        return null;
    }
}
