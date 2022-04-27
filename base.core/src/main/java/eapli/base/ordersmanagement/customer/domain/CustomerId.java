package eapli.base.ordersmanagement.customer.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import java.util.Objects;

public class CustomerId implements ValueObject, Comparable<CustomerId> {
    private String customerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerId that = (CustomerId) o;
        return Objects.equals(customerId, that.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }

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
