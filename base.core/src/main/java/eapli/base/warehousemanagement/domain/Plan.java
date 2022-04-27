package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embedded;

public class Plan implements ValueObject, Comparable<Plan> {

    @Column(insertable = false,updatable = false)
    @Embedded
    private WarehouseID warehouseID;
    private int length;
    private int width;
    private int square;
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
