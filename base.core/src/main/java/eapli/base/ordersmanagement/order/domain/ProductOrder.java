package eapli.base.ordersmanagement.order.domain;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.survey.domain.Survey;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class ProductOrder implements AggregateRoot<OrderID> {

    private Calendar dateTime;

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

    @Column(updatable = false,insertable = false)
    @Embedded
    private ShippingMethod shippingMethod;

    @Embedded
    private PriceOrder priceOrder;

    @Embedded
    private LineOrder lineOrder;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Survey survey;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Customer costumer;

    public ProductOrder(Calendar dateTime, Status status, OrderActor orderActor, PaymentMethod paymentMethod, ShippingMethod shippingMethod, PriceOrder priceOrder, LineOrder lineOrder, Survey survey, Customer costumer) {
        this.dateTime = dateTime;
        this.status = status;
        this.orderActor = orderActor;
        this.paymentMethod = paymentMethod;
        this.shippingMethod = shippingMethod;
        this.priceOrder = priceOrder;
        this.lineOrder = lineOrder;
        this.survey = survey;
        this.costumer = costumer;
    }

    public ProductOrder() {
    }

    @Override
    public String toString() {
        return "Order:" +
                "dateTime=" + dateTime +
                ", orderID=" + orderID +
                ", status=" + status +
                ", orderActor=" + orderActor +
                ", paymentMethod=" + paymentMethod +
                ", shippingMethod=" + shippingMethod +
                ", priceOrder=" + priceOrder +
                ", lineOrder=" + lineOrder +
                ", survey=" + survey +
                ", costumer=" + costumer;
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

    public Calendar getDateTime() {
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

    public Customer getCostumer() {
        return costumer;
    }

    public LineOrder getLineOrder() {
        return lineOrder;
    }

    public Survey getSurvey() {
        return survey;
    }


}