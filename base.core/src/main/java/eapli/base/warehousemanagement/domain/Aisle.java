package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Aisle implements ValueObject, Comparable<Aisle>  {

    private int aisleId;
    @Column(insertable = false,updatable = false)
    private int lsquareBegin;
    @Column(insertable = false,updatable = false)
    private int wsquareBegin;
    @Column(insertable = false,updatable = false)
    private int lsquareEnd;
    @Column(insertable = false,updatable = false)
    private int wsquareEnd;
    @Column(insertable = false,updatable = false)
    private int beginDepth;
    @Column(insertable = false,updatable = false)
    private int endDepth;
    @Column(insertable = false,updatable = false)
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


    @Override
    public int compareTo(Aisle o) {
        return 0;
    }
}
