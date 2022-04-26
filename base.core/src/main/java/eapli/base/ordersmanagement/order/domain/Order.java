package eapli.base.ordersmanagement.order.domain;


import eapli.base.ordersmanagement.product.domain.UniqueInternalCode;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Order implements AggregateRoot<OrderID> {

    private Date dateTime;


    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private OrderID orderID;

    @Embedded
    @Enumerated(EnumType.STRING)
    private Status status;

    @Embedded
    private OrderActor orderActor;

    @Embedded
    private PaymentMethod paymentMethod;

    @Embedded
    private ShippingMethod shippingMethod;

    @Embedded
    private PriceOrder priceOrder;

/*    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "survey_code")
    private Survey survey;*/

/*    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "costumer_id")
    private Costumer costumer;*/


    public Order() {
    }

    @Override
    public int compareTo(OrderID other) {
        return AggregateRoot.super.compareTo(other);
    }

    @Override
    public OrderID identity() {
        return this.orderID;
    }

    @Override
    public boolean hasIdentity(OrderID id) {
        return AggregateRoot.super.hasIdentity(id);
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    public OrderID orderID() {
        return identity();
    }

    public OrderID getOrderID() {
        return orderID;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public Status getStatus() {
        return status;
    }

    public OrderActor getOrderActor() {
        return orderActor;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    public PriceOrder getPriceOrder() {
        return priceOrder;
    }


}