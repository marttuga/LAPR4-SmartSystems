package eapli.base.ordersmanagement.costumer.domain;

import eapli.base.ordersmanagement.order.domain.OrderID;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Customer {
    @EmbeddedId
    private OrderID orderID;
}
