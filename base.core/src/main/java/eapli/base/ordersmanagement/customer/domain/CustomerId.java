package eapli.base.ordersmanagement.customer.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class CustomerId implements ValueObject, Comparable<CustomerId> {

    private String customerId;

    public CustomerId(final String customerId) {
        Preconditions.noneNull(customerId, "CustomerID cannot be null or empty");
        this.customerId = customerId;
    }

    protected CustomerId() {
    }

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

    @Override
    public String toString() {
        return  customerId ;
    }

    public static CustomerId valueOf(final String id){
        return new CustomerId(id);
    }
}
