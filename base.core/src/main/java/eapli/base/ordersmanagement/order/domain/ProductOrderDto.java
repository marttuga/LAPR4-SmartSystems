package eapli.base.ordersmanagement.order.domain;

import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.survey.domain.Survey;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

public class ProductOrderDto implements Serializable {

    private   OrderID orderID;
    private  Calendar dateTime;
    private  Status status;
    private  OrderActor orderActor;
    private  Customer customer;
    private  PaymentMethod paymentMethod;
    private  ShippingMethod shippingMethod;
    private  PriceOrder priceOrder;
    private   LineOrder lineOrder;
    private  Survey survey;



    public ProductOrderDto(OrderActor orderActor, OrderID orderID, Customer customer, Calendar dateTime, LineOrder lineOrder, PriceOrder priceOrder,
                           PaymentMethod paymentMethod, ShippingMethod shippingMethod, Status status, Survey survey) {
        this.dateTime = dateTime;
        this.orderID = orderID;
        this.status = status;
        this.orderActor = orderActor;
        this.customer = customer;
        this.paymentMethod = paymentMethod;
        this.shippingMethod = shippingMethod;
        this.priceOrder = priceOrder;
        this.lineOrder = lineOrder;
        this.survey = survey;
    }

    public OrderID getOrderID() {
        return orderID;
    }

    public Calendar getDateTimeDto() {
        return dateTime;
    }

    public Status getStatusDto() {
        return status;
    }

    public OrderActor getOrderActor() {
        return orderActor;
    }

    public Customer getCustomerDto() {
        return customer;
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

    public Survey getSurvey() {
        return survey;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOrderDto entity = (ProductOrderDto) o;
        return Objects.equals(this.orderID, entity.orderID) &&
                Objects.equals(this.customer, entity.customer) &&
                Objects.equals(this.orderActor, entity.orderActor) &&
                Objects.equals(this.dateTime, entity.dateTime) &&
                Objects.equals(this.status, entity.status);
    }

    public void changeStatus(Status status) {this.status = status;
    }

}
