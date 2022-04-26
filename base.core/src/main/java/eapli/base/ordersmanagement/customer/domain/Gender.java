package eapli.base.ordersmanagement.customer.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Gender implements ValueObject {
    @Embedded
    private String gender;

    public Gender() {

    }

    public Gender(String gender) {
        if (!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female") && !gender.equalsIgnoreCase("other")) {
            throw new IllegalArgumentException("Select only one male/female/other!");
        }
        this.gender = gender;
    }
}
