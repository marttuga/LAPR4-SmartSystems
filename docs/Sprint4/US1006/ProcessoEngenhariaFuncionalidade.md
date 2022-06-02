# US1006
=======================================

# 1. Requirements

**US1006** As Sales Clerk, I want to access a list of orders that had been dispatched for customer delivery and be able to update some of those orders to as being delivered.

This US is intended for any Sales Clerk that wishes to view the orders that that had been dispatched for customer delivery and change the status of a certain order.
In this case, the sales clerk can access a list of orders in the status "dispatched" and change their status to "delivered".
To simplify this requirement the sales clerk has ability to filter the orders by the status.

## Acceptance criteria
* 
* 


# 2. Analysis
- The Sales Clerk should be able to access the list of previous orders and check their status to see which ones have been dispatched for customer delivery 
- Then, the status of this orders must be changed to "delivered".



# 3. Design
* Utilizar a estrutura base standard da aplicação baseada em camadas 

* Domain Classes: ProductOrder
* Controller: OrdersDispatchedController
* Repository: ProductOrderRepository 
* Service: OrderService 
* UI: OrdersDispatchedUI 

## 3.1. Realization of Functionality

[US1006_SSD](/docs/Sprint4/US1006/US1006_SSD.svg)

[US1006_SD](/docs/Sprint4/US1006/US1006_SD.svg)

## 3.2. Class Diagram

[US1006_CD](/docs/Sprint4/US1006/US1006_CD.svg)

## 3.3. Applied Patterns

Repository factory, Controller, Service and GRASP.

## 3.4. Tests

**Test 1:** Change the status of an agv

    @Test
    void changeStatus() {
        Identifier id = new Identifier("1");
        AGV agV = new AGV(id, 70,agvDock,"auto", model, maxWeightCarry, Status.FREE );
        agV.changeStatus(Status.CHARGING);
        Assertions.assertEquals(Status.CHARGING, agV.getStatus());
    }

# 4. Implementation

* I implemented the domain classes based on the domain model created and that I needed for the US implementation. 
* I created the controller, repository and UI so that it was possible for the Sales Clerk to complete this
  task of ...


# 5. Integration/Demonstration

   

# 6. Observações



