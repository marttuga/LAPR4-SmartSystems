package eapli.base.order.domain;


import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Order implements AggregateRoot<OrderID> {

    @EmbeddedId
    private OrderID orderID;

    @OneToOne

}