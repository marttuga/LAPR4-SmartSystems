# US5002
=======================================


# 1. Requirements

**US5002** As Project Manager, I want that the team start developing the output communication module of the AGV digital twin to update its status on the "AGVManager".

I interpret that:
* It serves to develop an output communication module for the AGV digital twin in order to update the status of the AGVManager.

## Acceptance criteria
* It must be used the provided application protocol (SPOMS2022).
* The communication between the two involved components must be implemented in accordance with the SPOMS2022.

# 2. Analysis

- For the output part of the communication module, the information to be updated can be from the AGV status, that is, the AGVManager has the ability to update the
AGV status, in this case, for free.

# 3. Design

* Domain Classes: AGV

## 3.1. Realization of Functionality

[US5002_SD](/docs/Sprint3/US5002/US5002_SD.puml)

## 3.3. Applied Patterns


## 3.4. Tests
**Test 1:** Check if the agv status is correct

    @Test
    void changeStatus() {
    Identifier id = new Identifier("1");
    AGV agV = new AGV(id, 70,agvDock,"auto", model, maxWeightCarry, Status.FREE );
    agV.changeStatus(Status.CHARGING);
    Assertions.assertEquals(Status.CHARGING, agV.getStatus());
}

# 4. Implementation

- Create a server for AGVManager and an AGV Digital Twin client so that they could communicate with each other and, in this way,
- pass on the desired information to the purpose of the US.


# 5. Integration/Demonstration



