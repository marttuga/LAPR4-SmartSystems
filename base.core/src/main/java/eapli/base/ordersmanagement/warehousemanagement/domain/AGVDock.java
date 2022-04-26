package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class AGVDock implements ValueObject, Comparable<AGVDock>{

    private String agvDockID;
    private int begin;
    private int end;
    private int depth;
    private String accessibility;


    public AGVDock(final String agvDockID) {
        if (StringPredicates.isNullOrEmpty(agvDockID)) {
            throw new IllegalArgumentException(
                    "AGVDockID should neither be null nor empty");
        }
        this.agvDockID = agvDockID;
    }

    protected AGVDock() {
    }

    public AGVDock(String agvDockID, int begin, int end, int depth, String accessibility) {
        this.agvDockID = agvDockID;
        this.begin = begin;
        this.end = end;
        this.depth = depth;
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
