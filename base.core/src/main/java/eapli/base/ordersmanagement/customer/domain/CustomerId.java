package eapli.base.ordersmanagement.customer.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import java.util.Objects;

public class CustomerId implements ValueObject, Comparable<CustomerId> {
    private String customerId;

    public CustomerId(final String customerId) {
        Preconditions.noneNull(customerId, "CustomerID cannot be null or empty");
        this.customerId = customerId;
    }

    protected CustomerId() {
    }

    /*public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }*/

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






    @Override
    public int compareTo(CustomerId o) {
        return 0;
    }
}
