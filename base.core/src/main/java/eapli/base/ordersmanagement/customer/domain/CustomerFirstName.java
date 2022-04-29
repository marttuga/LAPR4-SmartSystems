package eapli.base.ordersmanagement.customer.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

public class CustomerFirstName implements ValueObject, Comparable<CustomerFirstName> {
    private String customerFirstName;

    protected CustomerFirstName(){
    }

    public CustomerFirstName(final String customerFirstName){
        if (StringPredicates.isNullOrEmpty(customerFirstName) ) {
            throw new IllegalArgumentException(
                    "Name attribute should neither be null nor empty");
        }
        this.customerFirstName = customerFirstName;
    }

    public static CustomerFirstName valueOf(final String customerFirstName) {
        return new CustomerFirstName(customerFirstName);
    }

    @Override
    public String toString() {
        return String.format("%s", customerFirstName);
    }


    @Override
    public int compareTo(CustomerFirstName o) {
        return 0;
    }
}
