package eapli.base.warehousemanagement.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AGVTest {

    Identifier identifier = new Identifier("2");
    Model model = new Model("x");
    MaxWeightCarry maxWeightCarry = new MaxWeightCarry(120);
    AGVDock agvDock = new AGVDock("D1", 1, 3, 1, 3, 1, 3, "l+");
    AGV agv = new AGV(identifier, 90, agvDock, "automatic", model, maxWeightCarry );

    @Test
    void getIdentifier() {
        Identifier id = new Identifier("1");
        AGV agV = new AGV(id, 70,agvDock,"auto", model, maxWeightCarry );
        Assertions.assertEquals(id, agV.getIdentifier());

    }

    @Test
    void getAutonomy() {
        Identifier id = new Identifier("1");
        AGV agV = new AGV(id, 70,agvDock,"auto", model, maxWeightCarry );
        Assertions.assertEquals(70, agV.getAutonomy());
    }

    @Test
    void getAgvDock() {
        Identifier id = new Identifier("1");
        AGV agV = new AGV(id, 70,agvDock,"auto", model, maxWeightCarry );
        Assertions.assertEquals(agvDock, agV.getAgvDock());
    }

    @Test
    void getAGVDescription() {
        Identifier id = new Identifier("1");
        AGV agV = new AGV(id, 70,agvDock,"auto", model, maxWeightCarry );
        Assertions.assertEquals("auto", agV.getAGVDescription());
    }

    @Test
    void getModel() {
        Identifier id = new Identifier("1");
        AGV agV = new AGV(id, 70,agvDock,"auto", model, maxWeightCarry );
        Assertions.assertEquals(model, agV.getModel());
    }

    @Test
    void getMaxWeightCarry() {
        Identifier id = new Identifier("1");
        AGV agV = new AGV(id, 70,agvDock,"auto", model, maxWeightCarry );
        Assertions.assertEquals(maxWeightCarry, agV.getMaxWeightCarry());
    }
}