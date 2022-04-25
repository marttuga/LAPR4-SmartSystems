package eapli.base.ordersmanagement.order.domain;


import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Order implements AggregateRoot<OrderID> {

    private Date dateTime;
    private int priceOrder;

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private OrderID orderID;

    @Embedded
    @Enumerated(EnumType.STRING)
    private Status status;

    @Embedded
    private OrderActor orderActor;

  /*  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "paymentMethod_id")
    private PaymentMethod paymentMethod;*/

/*    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "survey_code")
    private Survey survey;*/

/*    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "costumer_id")
    private Costumer costumer;*/

    public Order() {
    }


    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public int compareTo(OrderID other) {
        return AggregateRoot.super.compareTo(other);
    }

    @Override
    public OrderID identity() {
        return null;
    }

    @Override
    public boolean hasIdentity(OrderID id) {
        return AggregateRoot.super.hasIdentity(id);
    }

    ;


}