package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embedded;

public class Plan implements ValueObject, Comparable<Plan> {

    @Embedded
    private WarehouseID warehouseID;

    private int length;
    private int width;
    private int aisleId;
    private int aisleBegin;
    private int aisleEnd;
    private int aisleDepth;
    private String aisleAccessibility;
    private int rowId;
    private int rowBegin;
    private int rowEnd;
    private int shelfId;

    @Embedded
    private AGVDock AGVDock;

    public Plan(WarehouseID warehouseID, int aisleId, int rowId, int shelfId) {
        this.warehouseID = warehouseID;
        this.aisleId = aisleId;
        this.rowId = rowId;
        this.shelfId = shelfId;
    }

    public Plan(WarehouseID warehouseID, int length, int width,
                int aisleId, int aisleBegin, int aisleEnd, int aisleDepth,
                String aisleAccessibility, int rowId, int rowBegin, int rowEnd,
                eapli.base.warehousemanagement.domain.AGVDock AGVDock) {
        this.warehouseID = warehouseID;
        this.length = length;
        this.width = width;
        this.aisleId = aisleId;
        this.aisleBegin = aisleBegin;
        this.aisleEnd = aisleEnd;
        this.aisleDepth = aisleDepth;
        this.aisleAccessibility = aisleAccessibility;
        this.rowId = rowId;
        this.rowBegin = rowBegin;
        this.rowEnd = rowEnd;
        this.AGVDock = AGVDock;
    }

    @Override
    public int compareTo(Plan o) {
        return 0;
    }
}
