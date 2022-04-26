package eapli.base.ordersmanagement.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embedded;

public class AGV_Warehouse implements ValueObject ,Comparable <AGV_Warehouse> {

    @Embedded
    private Identifier identifier;

    public AGV_Warehouse(final String identifier) {
        if (StringPredicates.isNullOrEmpty(identifier)) {
        throw new IllegalArgumentException(
        "Identifier of AGV should neither be null nor empty");
        }
        this.identifier = Identifier.valueOf(identifier);
        }

    protected AGV_Warehouse() {
        }

    @Override
    public int compareTo(AGV_Warehouse o) {
        return 0;
    }
}
