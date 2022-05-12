package eapli.base.ordersmanagement.order.domain;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.survey.domain.Survey;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import org.hibernate.type.CalendarDateType;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class ProductOrder implements AggregateRoot<OrderID> {

    private Calendar dateTime;

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private OrderID orderID;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Embedded
    private OrderActor orderActor;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;


    @Enumerated(EnumType.STRING)
    private ShippingMethod shippingMethod;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "priceMoney.amount", column = @Column(name = "priceMoneyAmount")),
            @AttributeOverride(name = "priceMoney.currency", column = @Column(name = "priceMoneyCurrency"))
    })
    private PriceOrder priceOrder;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private LineOrder lineOrder;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Survey survey;


    public ProductOrder(OrderActor orderActor, OrderID orderID, Customer customer, Calendar dateTime, LineOrder lineOrder, PriceOrder priceOrder,
                        PaymentMethod paymentMethod, ShippingMethod shippingMethod, Status status) {
        this.dateTime = dateTime;
        this.orderID = orderID;
        this.status = status;
        this.orderActor = orderActor;
        this.customer = customer;
        this.paymentMethod = paymentMethod;
        this.shippingMethod = shippingMethod;
        this.priceOrder = priceOrder;
        this.lineOrder = lineOrder;
    }

    protected ProductOrder() {
    }


    @Override
    public String toString() {
        return "Order:" +
                "\ndateTime="  + dateTime.getTime() +
                "\norderID=" + orderID +
                "\nstatus=" + status +
                "\norderActor=" + orderActor +
                "\npaymentMethod=" + paymentMethod +
                "\nshippingMethod=" + shippingMethod +
                "\npriceOrder=" + priceOrder +
                "\nlineOrder=" + lineOrder +
                "\ncostumer=" + customer;
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

    public Status getStatus() {
        return status;
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


    public LineOrder getLineOrder() {
        return lineOrder;
    }


}