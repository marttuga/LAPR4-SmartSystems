package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.*;

@Entity
public class Aisle implements AggregateRoot<Integer>  {

    @Id
    private int aisleId;
    @Column(insertable = false,updatable = false)
    @Transient
    private int lsquareBegin;
    @Column(insertable = false,updatable = false)
    @Transient
    private int wsquareBegin;
    @Column(insertable = false,updatable = false)
    @Transient
    private int lsquareEnd;
    @Column(insertable = false,updatable = false)
    @Transient
    private int wsquareEnd;
    @Column(insertable = false,updatable = false)
    @Transient
    private int beginDepth;
    @Column(insertable = false,updatable = false)
    @Transient
    private int endDepth;
    @Column(insertable = false,updatable = false)
    @Transient
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

    public Aisle(int aisleId, int lsquareBegin, int wsquareBegin,
                 int lsquareEnd, int wsquareEnd,
                 int beginDepth, int endDepth, String accessibility) {
        this.aisleId = aisleId;
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

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Integer identity() {
        return null;
    }
}
