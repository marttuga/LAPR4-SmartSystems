# US1902
=======================================

# 1. Requirements

**US1902** As Project Manager, I want the communications made through the SPOMS2022 protocol to be secured/protected.

The interpretation made of this requirement was that the project manager wishes that the communications made through the 
SPOMS2022 protocol are secured/protected using mutual authentication based on public key certificates.
SSL/TLS is a protocol designed to enforce security in network communications at layer four, meaning over
UDP and over TCP, so, unlike IPsec operating at layer three, it’s directly used by network applications.

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

>   Classes do domínio: CsvProtocolProxy, BookingToken
>
>   Controlador: CsvProtocolProxyController;
>
>   Server: OrderServer
> 
>   Client: CustomerAppCli


## 3.2. Applied Patterns

* Proxy,Controller,Grasp

# 4. Implementation
* It should be adopted SSL/TLS with mutual authentication based on public key certificates.
* It complements the US1901.# 2. Analysis* To start, we changed the Sockets to SSLSocket and the ServerSockets to SSLServerSocket.
* Then we created the TrustStore and KeyStore for both the client and the server with a given password, and respective certificates.
* Also, in both the server and the client we linked the properties with the .jks files.




