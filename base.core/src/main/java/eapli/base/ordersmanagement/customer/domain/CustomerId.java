package eapli.base.ordersmanagement.customer.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

public class CustomerId implements ValueObject, Comparable<CustomerId> {
    private String customerId;

    public CustomerId(final String customerId) {
        if (StringPredicates.isNullOrEmpty(customerId)) {
            throw new IllegalArgumentException(
                    "CustomerId should neither be null nor empty");
        }
        this.customerId = customerId;
    }

    protected CustomerId() {
        // for ORM
    }

    public static CustomerId valueOf(final String customerId) {
        return new CustomerId(customerId);
    }

    @Override
    public String toString() {
        return this.customerId;
    }


    @Override
    public int compareTo(CustomerId o) {
        return 0;
    }
}
