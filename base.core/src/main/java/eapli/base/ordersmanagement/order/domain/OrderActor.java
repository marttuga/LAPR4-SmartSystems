package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class OrderActor implements ValueObject {

    private String id;
    private Role role;


    public static enum Role {
        Costumer, Sales_Clerk
    };

    public OrderActor() {
    }

    public OrderActor(String id,Role role) {
        this.id = id;
        this.role = role;
    }
}
