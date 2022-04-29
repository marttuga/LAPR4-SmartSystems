package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class AGVDock implements ValueObject, Comparable<AGVDock>{

    @Transient
    private int agvDockID;
    @Transient
    private int lsquareBeginAGVDock;
    @Transient
    private int wsquareBeginAGVDock;
    @Transient
    private int lsquareEndAGVDock;
    @Transient
    private int wsquareEndAGVDock;
    @Transient
    private int lsquareDepthAGVDock;
    @Transient
    private int wsquareDepthAGVDock;
    @Column(insertable = false, updatable = false)
    @Transient
    private String accessibility;


    public AGVDock(final String agvDockID) {
        if (StringPredicates.isNullOrEmpty(agvDockID)) {
            throw new IllegalArgumentException(
                    "AGVDockID should neither be null nor empty");
        }
        this.agvDockID = Integer.parseInt(agvDockID);
    }

    protected AGVDock(int agvDockID) {
    }

    public AGVDock(int agvDockID, int lsquareBeginAGVDock, int wsquareBeginAGVDock,
                   int lsquareEndAGVDock, int wsquareEndAGVDock, int lsquareDepthAGVDock,
                   int wsquareDepthAGVDock, String accessibility) {
        this.agvDockID = agvDockID;
        this.lsquareBeginAGVDock = lsquareBeginAGVDock;
        this.wsquareBeginAGVDock = wsquareBeginAGVDock;
        this.lsquareEndAGVDock = lsquareEndAGVDock;
        this.wsquareEndAGVDock = wsquareEndAGVDock;
        this.lsquareDepthAGVDock = lsquareDepthAGVDock;
        this.wsquareDepthAGVDock = wsquareDepthAGVDock;
        this.accessibility = accessibility;
    }

    protected AGVDock() {

    }


    public static AGVDock valueOf(final int agvDockID) {
        return new AGVDock(agvDockID);
    }

    @Override
    public int compareTo(AGVDock o) {
        return 0;
    }
}
