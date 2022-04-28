package eapli.base.ordersmanagement.customer.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import java.util.Objects;

public class MecanographicNumber implements ValueObject, Comparable<MecanographicNumber> {
    private int customerId;

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MecanographicNumber that = (MecanographicNumber) o;
        return Objects.equals(customerId, that.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }

    public MecanographicNumber(final int customerId) {
        Preconditions.noneNull(customerId, "CustomerID cannot be null or empty");
        this.customerId = customerId;
    }

    protected MecanographicNumber() {
        // for ORM
    }




    @Override
    public int compareTo(MecanographicNumber o) {
        return 0;
    }
}
