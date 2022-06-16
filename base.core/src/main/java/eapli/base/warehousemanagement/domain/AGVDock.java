package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.*;

@Entity
public class AGVDock implements AggregateRoot<String> {

    @Id
    private String agvDockID;
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
        this.agvDockID = agvDockID;
    }

    public AGVDock(int agvDockID) {
    }

    public AGVDock(String agvDockID, int lsquareBeginAGVDock, int wsquareBeginAGVDock,
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

    public String getAgvDockID() {
        return agvDockID;
    }

    public int getLsquareBeginAGVDock() {
        return lsquareBeginAGVDock;
    }

    public int getWsquareBeginAGVDock() {
        return wsquareBeginAGVDock;
    }

    public int getLsquareEndAGVDock() {
        return lsquareEndAGVDock;
    }

    public int getWsquareEndAGVDock() {
        return wsquareEndAGVDock;
    }

    public int getLsquareDepthAGVDock() {
        return lsquareDepthAGVDock;
    }

    public int getWsquareDepthAGVDock() {
        return wsquareDepthAGVDock;
    }

    public String getAccessibility() {
        return accessibility;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public String identity() {
        return null;
    }
}
