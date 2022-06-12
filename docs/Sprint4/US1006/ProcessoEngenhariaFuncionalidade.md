# US1006
=======================================

# 1. Requirements

**US1006** As Sales Clerk, I want to access a list of orders that had been dispatched for customer delivery and be able to update some of those orders to as being delivered.

This US is intended for any Sales Clerk that wishes to view the orders that that had been dispatched for customer delivery and change the status of a certain order.
In this case, the sales clerk can access a list of orders in the status "dispatched" and change their status to "delivered".
To simplify this requirement the sales clerk has ability to filter the orders by the status.

## Acceptance criteria
* I would say that some fields might be the same (e.g. order id, date, customer) and differ on others.
* For instance, within this US the dispatching' date seems to be an important information.


# 2. Analysis
- The Sales Clerk should be able to access the list of previous orders and check their status to see which ones have been dispatched for customer delivery 
- Then, the status of this orders must be changed to "delivered".
- When collecting orders in the 'dispatched' status, the customer can choose to change their status to 'delivered', and thus save that order in the database
with the status changed.


# 3. Design
*  Use the standard base structure of the layer-based application.

* Domain Classes: ProductOrder
* Controller: OrdersDispatchedController
* Repository: ProductOrderRepository 
* Service: OrderService 
* UI: OrdersDispatchedUI 

## 3.1. Realization of Functionality

[US1006_SSD](US1006_SSD.svg)

[US1006_SD](US1006_SD.svg)

## 3.2. Class Diagram

[US1006_CD](US1006_CD.svg)

## 3.3. Applied Patterns

Repository factory, Controller, Service, GRASP and DTOs.

## 3.4. Tests

**Test 1:** Change the status of an order to 'DISPATCHED'

     @Test
    void changeStatus1() {
        ProductOrder order = new ProductOrder(o, orderID, c, d, lineOrder, po, PaymentMethod.MBWAY, ShippingMethod.Blue, Status.PREPARED);
        order.changeStatus(Status.DISPATCHED);
        Assertions.assertEquals(Status.DISPATCHED, order.getStatus());
    }

**Test 2:** Change the status of an order to confirm that it is not the same
  
     @Test
    void changeStatus3() {
        ProductOrder order = new ProductOrder(o, orderID, c, d, lineOrder, po, PaymentMethod.MBWAY, ShippingMethod.Blue, Status.DISPATCHED);
        order.changeStatus(Status.DELIVERED);
        Assertions.assertNotEquals(Status.DISPATCHED, order.getStatus());
    }
    

# 4. Implementation

* I implemented the domain classes (Product Order) based on the domain model created and that I needed for the US implementation. 
* I created the controller, repository and UI so that it was possible for the Sales Clerk to complete this
  task of change status of an order.
* I created DTOs of class Product Order.


# 5. Integration/Demonstration
Method 'toDTO' 

    public ProductOrderDto toDTO(){
      return new ProductOrderDto(this.orderActor, this.orderID, this.customer, this.dateTime, this.lineOrder,
      this.priceOrder, this.paymentMethod, this.shippingMethod, this.status, this.survey);
    }



