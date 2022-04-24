package eapli.base.ordersmanagement.order.domain;


import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;

@Entity
public class Order implements AggregateRoot<OrderID> {

    @EmbeddedId
    private OrderID orderID;
    @Embedded
    private Status status;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "paymentMethod_id")
    private PaymentMethod paymentMethod;

    public Order() {
    }

    ;


}