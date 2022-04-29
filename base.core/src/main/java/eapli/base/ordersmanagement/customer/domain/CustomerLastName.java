package eapli.base.ordersmanagement.customer.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerLastName implements ValueObject, Comparable<CustomerLastName>{

    private String customerLastName;

    protected CustomerLastName() {

    }

    public CustomerLastName(final String customerLastName) {
        if (StringPredicates.isNullOrEmpty(customerLastName) ) {
            throw new IllegalArgumentException(
                    "Name attribute should neither be null nor empty");
        }
        this.customerLastName = customerLastName;
    }


    public static CustomerLastName valueOf(final String customerLastName) {
        return new CustomerLastName(customerLastName);
    }

    @Override
    public String toString() {
        return String.format("%s", customerLastName);
    }

    @Override
    public int compareTo(CustomerLastName o) {
        return 0;
    }
}
