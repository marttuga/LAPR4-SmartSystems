package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class WarehouseID implements ValueObject, Comparable<WarehouseID>{


    private String warehouseID;

    public WarehouseID(final String warehouseIdentification) {
        if (StringPredicates.isNullOrEmpty(warehouseIdentification)) {
            throw new IllegalArgumentException(
                    "WarehouseID should neither be null nor empty");
        }
        this.warehouseID = warehouseIdentification;
    }

    protected WarehouseID() {
    }

    public static WarehouseID valueOf(final String warehouseIdentification) {
        return new WarehouseID(warehouseIdentification);
    }


    @Override
    public int compareTo(WarehouseID o) {
        return 0;
    }

    @Override
    public String toString() {
        return "WarehouseID: " + warehouseID;
    }
}
