package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class Shelf implements ValueObject, Comparable<Shelf>{
    private int shelfId;
    private int nrShelves;

    public Shelf(final String shelfId) {
        if (StringPredicates.isNullOrEmpty(shelfId)) {
            throw new IllegalArgumentException(
                    "ShelfID should neither be null nor empty");
        }
        this.shelfId = Integer.parseInt(shelfId);
    }

    protected Shelf() {
    }

    public Shelf(int shelfId, int nrShelves) {
        this.shelfId = shelfId;
        this.nrShelves = nrShelves;
    }

    public static Shelf valueOf(final String shelfId) {
        return new Shelf(shelfId);
    }

    @Override
    public int compareTo(Shelf o) {
        return 0;
    }

    public int getShelfId() {
        return shelfId;
    }

    public int getNrShelves() {
        return nrShelves;
    }
}
