package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class OrderActor implements ValueObject {

    private String email;
    private Role role;


    public static enum Role {
        Costumer, Sales_Clerk
    };

    protected OrderActor() {
    }

    public OrderActor(String email, Role role) {
        this.email = email;
        this.role = role;
    }

    @Override
    public String toString() {
        return  email + " | " +role;
    }
}
