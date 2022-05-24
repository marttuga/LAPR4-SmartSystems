# US2004
=======================================

# 1. Requirements

**US2004** As Warehouse Employee, I want to access the list of orders that have already been prepared by the AGVs and be able to update any of those orders as having been dispatched for customer delivery.

This User Story is intended for any Warehouse Employee that wishes to change the status of a certain order.
In this case, the employee can access a list of orders in the status "already been prepared by the AGV" and change their status to "dispatched for customer delivery".
To simplify this requirement the employee has ability to filter the orders by the AGV that has collected those products.

## Acceptance criteria
* The ability to filter a certain order by an AGV


# 2. Analysis
* To complete this user story, the US1004 (responsable for registering new orders), US2002 (responsible for configuring the AGVs) and US2003(responsible for configuring the AGVs) are going to be necessary.
* The warehouse employee should be able to access the list of previous orders and check their status to see which ones have already been prepared by the AGV
* Then, the status of this orders must be changed to "dispatched for customer delivery"


# 3. Design
* Utilizar a estrutura base standard da aplicação baseada em camadas 

>   Classes do domínio: ProductOrder, AGV
>
>   Controlador: AGVToPrepOrderController
>
>   Repository:  ProductOrderRepository
>  
>   UI:  DispatchOrdersUI

## 3.1. Realização da Funcionalidade

![SD](US2004_SD.svg)

## 3.2. Diagrama de Classes
![CD](US2004_CD.svg)

## 3.3. Padrões Aplicados

Repository factory to store in database and controller.

## 3.4. Testes
**Teste 1:** Change the status of an agv

    @Test
    void changeStatus() {
        Identifier id = new Identifier("1");
        AGV agV = new AGV(id, 70,agvDock,"auto", model, maxWeightCarry, Status.FREE );
        agV.changeStatus(Status.CHARGING);
        Assertions.assertEquals(Status.CHARGING, agV.getStatus());
    }

# 4. Implementação

* I implemented the domain classes based on the domain model created and that I needed for the US implementation. 
* I created the controller, repository and UI so that it was possible for the warehouse employee to complete this
  task of giving an order to be prepared to an agv. 

# 5. Integração/Demonstração

    public AGV agvToPrepOrder(AGV agv,ProductOrder productOrder) {
        agv.changeOrder(productOrder);
        agv.changeStatus(Status.OCCUPIED);
        agvRepository.save(agv);
        return agv;
    }
# 6. Observações



