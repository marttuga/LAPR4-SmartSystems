package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class AGVDock implements ValueObject, Comparable<AGVDock>{

    private int agvDockID;
    private int lsquareBeginAGVDock;
    private int wsquareBeginAGVDock;
    private int lsquareEndAGVDock;
    private int wsquareEndAGVDock;
    private int lsquareDepthAGVDock;
    private int wsquareDepthAGVDock;
    private String accessibility;


    public AGVDock(final String agvDockID) {
        if (StringPredicates.isNullOrEmpty(agvDockID)) {
            throw new IllegalArgumentException(
                    "AGVDockID should neither be null nor empty");
        }
        this.agvDockID = Integer.parseInt(agvDockID);
    }

    protected AGVDock() {
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


    public static AGVDock valueOf(final String agvDockID) {
        return new AGVDock(agvDockID);
    }

    @Override
    public int compareTo(AGVDock o) {
        return 0;
    }
}
