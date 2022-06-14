package eapli.base.ordersmanagement.order.domain;

import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.survey.domain.Survey;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

public class ProductOrderDto implements Serializable {

    private   OrderID orderID;
    private  Status status;


    public ProductOrderDto( OrderID orderID,Status status) {
        this.orderID = orderID;
        this.status = status;

    }

    public OrderID getOrderID() {
        return orderID;
    }

    public Status getStatusDto() {
        return status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOrderDto that = (ProductOrderDto) o;
        return Objects.equals(orderID, that.orderID) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, status);
    }

    public void changeStatus(Status status) {this.status = status;
    }

}
