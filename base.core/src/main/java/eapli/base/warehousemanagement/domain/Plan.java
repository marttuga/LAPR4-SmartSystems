package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Transient;

public class Plan implements ValueObject, Comparable<Plan> {

    @Column(insertable = false,updatable = false)
    private WarehouseID warehouseID;
    @Transient
    private int length;
    @Transient
    private int width;
    @Transient
    private int square;
    @Transient
    private String unit;


    public Plan( int length, int width, int square, String unit) {
        this.length = length;
        this.width = width;
        this.square = square;
        this.unit = unit;
    }


    protected Plan() {

    }

    public Plan(WarehouseID warehouseID, int length, int width, int square,
                String unit) {
        this.warehouseID = warehouseID;
        this.length = length;
        this.width = width;
        this.square = square;
        this.unit = unit;
    }

    @Override
    public int compareTo(Plan o) {
        return 0;
    }
}
