# US2001
=======================================

# 1. Requirements

**US2001** As Warehouse Employee, I want to set up the warehouse plan by uploading a JSON file.

- Create a warehouse plan by uploading a JSON file that has the warehouse layout/organization.
  
I interpret that:
* Each row states how many shelves it has. As so, if a row states it has 4 shelves, it means that the row shelves are identified as shelf 1, 2, 3 and 4 from the bottom to the top.
* As shelves in rows are identified from bottom to top.

## Acceptance criteria
* A default file can be loaded automatically.
* If a JSON file is found may be updated the information about the warehouse plan.
* Warehouse plant persistence must be done in the database because the JSON file might not be available on all workstations used by warehouse employees.
* US 2001 is just to set up the warehouse plant by uploading a file. As so, it is not necessary to graphically display the warehouse plant.


# 2. Analysis

- Creation of a JSON file that contains all the information necessary for the location of any product in a warehouse, that is, having all the storage areas defined (with coordinates).
- In addition, facilitate the user's request to open and update the information of the warehouse plan and finally save it.


# 3. Design

* Domain Classes: Warehouse, WarehouseID, AGVDock, Plan, Row, Shelf, Aisle
* Controller: UploadFileController
* Service : UploadFileService
* Repository: UploadFileRepository


## 3.1. Realização da Funcionalidade

[US2001_SD](/docs/Sprint2/US2001/US2001_SD.puml)

[US2001_SSD](/docs/Sprint2/US2001/US2001_SSD.puml)

## 3.2. Diagrama de Classes

[US2001_CD](/docs/Sprint2/US2001/US2001_CD.puml)

## 3.3. Padrões Aplicados

Repository factory to store in database and controller.

## 3.4. Testes 

**Test 1:** Check that the value of  warehouse id is 2.

    @Test
    void valueOf() {
        WarehouseID war = new WarehouseID("2");
        Assertions.assertEquals(WarehouseID.valueOf("2"),war );
    }

**Test 2:** Check that the length of warehouse's plan is not the same to plan and plan 1.

    @Test
    void testNotEqualsLength() {
        Plan p = new Plan(20000, 15000, 100, "cm");
        Plan p1 = new Plan(10000, 15000, 100, "cm");
        Assertions.assertNotEquals(p.getLength(), p1.getLength());
    }


# 4. Implementação

* I implemented the domain classes based on the domain model created and that I needed for the US implementation. Thus, by uploading the JSON file, it will be possible to configure the warehouse's plan, which includes aisle, row and agv dock.
After that, I created the controller, repository and UI so that it was possible for the warehouse employee to complete this 
task (upload file). A JSON file was also created with the warehouse settings that we defined as a team.

# 5. Integração/Demonstração

* The concern of not persisting the data read when uploading the JSON file was taken into account.
* I integrated this US into the warehouse employee menu.

# 6. Observações




