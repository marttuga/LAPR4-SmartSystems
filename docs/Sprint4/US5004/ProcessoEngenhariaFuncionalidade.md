# US5004
=======================================

# 1. Requirements

**US5004** As Project Manager, I want the output communications (of the AGV Digital Twin) made through the SPOMS2022 protocol to be secured/protected.

This US is intended for any Project Manager that wishes to establishing secure communications between two network applications (server and client).
In this case, the project manager can use a set of mechanisms to ensure authentication, privacy and integrity.

## Acceptance criteria
* The AGV does not communicate directly with the orders database. You solution must conform this representation.
* 


# 2. Analysis
- The Project Manager can use a pre-shared key or public key certificates.
- So basically, we have insecure TCP connections and SSL/TLS will transform them into secure TCP connections by a coordinated effort on both sides (client and server)


# 3. Design
*  Use the standard base structure of the layer-based application.

* Controller: OrdersDispatchedController

## 3.1. Realization of Functionality

[US5004_SD](/docs/Sprint4/US5004/US5004_SD.svg)

## 3.3. Applied Patterns

Controller

## 3.4. Tests

**Test 1:** 

    @Test
    

# 4. Implementation

* 


# 5. Integration/Demonstration

   

# 6. Observações



