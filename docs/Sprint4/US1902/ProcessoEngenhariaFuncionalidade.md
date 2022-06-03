# US1902
=======================================

# 1. Requirements

**US1902** As Project Manager, I want the communications made through the SPOMS2022 protocol to be secured/protected.

The interpretation made of this requirement was that the project manager wishes that the communications made through the 
SPOMS2022 protocol are secured/protected using mutual authentication based on public key certificates.

## Acceptance criteria
* It must be used the provided application protocol (SPOMS2022).
* It should be adopted SSL/TLS with mutual authentication based on public key certificates.
* It complements the US1901.


# 2. Analysis
* To complete this user story, the  us1901 (responsible for the "OrdersServer" component) is going to be necessary so 
that the communications made through the SPOMS2022 protocol can be secured/protected.
* This should be implemented using SSL/TLS with mutual authentication based on public key certificates in order to complement the US1901.


# 3. Design
* Utilizar a estrutura base standard da aplicação baseada em camadas 
 
>   Server: OrderServer
> 
>   Client: CustomerAppCli

## 3.1. Realization of Functionality

![SD](US1902_SD.svg)

## 3.2. Applied Patterns

* It will be used SSL/TLS with mutual authentication based on public key certificates 
* Proxy

# 4. Implementation

  



