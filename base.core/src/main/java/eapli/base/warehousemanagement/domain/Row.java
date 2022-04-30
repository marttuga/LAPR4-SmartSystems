package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Row implements ValueObject, Comparable<Row> {

    @Transient
    private int rowId;
    @Transient
    @Column(insertable = false,updatable = false)
    private int lsquareBegin;
    @Transient
    @Column(insertable = false,updatable = false)
    private int wsquareBegin;
    @Transient
    @Column(insertable = false,updatable = false)
    private int lsquareEnd;
    @Transient
    @Column(insertable = false,updatable = false)
    private int wsquareEnd;

    public Row(final String rowId) {
        if (StringPredicates.isNullOrEmpty(rowId)) {
            throw new IllegalArgumentException(
                    "RowID should neither be null nor empty");
        }
        this.rowId = Integer.parseInt(rowId);
    }

    protected Row() {
    }

    public Row(int rowId, int lsquareBegin, int wsquareBegin,
              int lsquareEnd, int wsquareEnd) {
        this.rowId = rowId;
        this.lsquareBegin = lsquareBegin;
        this.wsquareBegin = wsquareBegin;
        this.lsquareEnd = lsquareEnd;
        this.wsquareEnd = wsquareEnd;
    }

    public static Row valueOf(final String rowId) {
        return new Row(rowId);
    }


    @Override
    public int compareTo(Row o) {
        return 0;
    }
}
