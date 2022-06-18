package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.*;

@Entity
public class Aisle implements AggregateRoot<Integer>  {

    @Id
    private int aisleId;

    @Embedded
    private WarehouseID warehouseID;

    private int lsquareBegin;

    private int wsquareBegin;

    private int lsquareEnd;

    private int wsquareEnd;

    private int beginDepth;

    private int endDepth;

    private String accessibility;

    public Aisle(final String aisleId) {
        if (StringPredicates.isNullOrEmpty(aisleId)) {
            throw new IllegalArgumentException(
                    "AisleID should neither be null nor empty");
        }
        this.aisleId = Integer.parseInt(aisleId);
    }

    protected Aisle() {
    }

    public Aisle(int aisleId, WarehouseID warehouseID, int lsquareBegin, int wsquareBegin,
                 int lsquareEnd, int wsquareEnd,
                 int beginDepth, int endDepth, String accessibility) {
        this.aisleId = aisleId;
        this.warehouseID = warehouseID;
        this.lsquareBegin = lsquareBegin;
        this.wsquareBegin = wsquareBegin;
        this.lsquareEnd = lsquareEnd;
        this.wsquareEnd = wsquareEnd;
        this.beginDepth = beginDepth;
        this.endDepth = endDepth;
        this.accessibility = accessibility;
    }

    public static Aisle valueOf(final String aisleId) {
        return new Aisle(aisleId);
    }

    public int getAisleId() {
        return aisleId;
    }

    public int getLsquareBegin() {
        return lsquareBegin;
    }

    public int getWsquareBegin() {
        return wsquareBegin;
    }

    public int getLsquareEnd() {
        return lsquareEnd;
    }

    public int getWsquareEnd() {
        return wsquareEnd;
    }

    public int getBeginDepth() {
        return beginDepth;
    }

    public int getEndDepth() {
        return endDepth;
    }

    public String getAccessibility() {
        return accessibility;
    }

    public WarehouseID getWarehouseID() {
        return warehouseID;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Integer identity() {
        return null;
    }
}
