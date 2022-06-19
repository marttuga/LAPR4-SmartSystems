# US5003
=======================================

# 1. Requirements

**US5003** As Project Manager, I want the input communications (of the AGV Digital Twin) made through the SPOMS2022 protocol to be secured/protected.

For this US, the fellow connection between the AgvManager (client) and the AgvDigitalTwin (server) should be secured/protected.

## Acceptance criteria
* It should be adopted SSL/TLS with mutual authentication based on public key certificates.
* It complements the US5001.

# 2. Analysis

* To start, we changed the Sockets to SSLSocket and the ServerSockets to SSLServerSocket.
* Then we created the TrustStore and KeyStore for both the client and the server with a given password, and respective certificates.
* Also, in both the server and the client we linked the properties with the .jks files.
