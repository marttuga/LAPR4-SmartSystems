package eapli.base.warehousemanagement.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AGVDockTest {

    @Test
    void valueOf() {
        AGVDock agvDock = new AGVDock("D1",new WarehouseID("1"), 1, 3, 1, 3, 1, 3, "l+");
        Assertions.assertEquals("D1",agvDock.getAgvDockID() );
    }


    @Test
    void getLsquareBeginAGVDock() {
        AGVDock agvDock = new AGVDock("D1",new WarehouseID("1"), 1, 3, 1, 3, 1, 3, "l+");
        Assertions.assertEquals(1,agvDock.getLsquareBeginAGVDock() );
    }

    @Test
    void getWsquareBeginAGVDock() {
        AGVDock agvDock = new AGVDock("D1",new WarehouseID("1"), 1, 3, 1, 3, 1, 3, "l+");
        Assertions.assertEquals(3,agvDock.getWsquareBeginAGVDock() );
    }

    @Test
    void getLsquareEndAGVDock() {
        AGVDock agvDock = new AGVDock("D1",new WarehouseID("1"), 1, 3, 1, 3, 1, 3, "l+");
        Assertions.assertEquals(1,agvDock.getLsquareEndAGVDock() );
    }

    @Test
    void getWsquareEndAGVDock() {
        AGVDock agvDock = new AGVDock("D1",new WarehouseID("1"), 1, 3, 1, 3, 1, 3, "l+");
        Assertions.assertEquals(3,agvDock.getWsquareEndAGVDock() );
    }

    @Test
    void getLsquareDepthAGVDock() {
        AGVDock agvDock = new AGVDock("D1",new WarehouseID("1"), 1, 3, 1, 3, 1, 3, "l+");
        Assertions.assertEquals(1,agvDock.getLsquareDepthAGVDock() );
    }

    @Test
    void getWsquareDepthAGVDock() {
        AGVDock agvDock = new AGVDock("D1",new WarehouseID("1"), 1, 3, 1, 3, 1, 3, "l+");
        Assertions.assertEquals(3,agvDock.getWsquareDepthAGVDock() );
    }

    @Test
    void getAccessibility() {
        AGVDock agvDock = new AGVDock("D1",new WarehouseID("1"), 1, 3, 1, 3, 1, 3, "l+");
        Assertions.assertEquals("l+",agvDock.getAccessibility() );
    }

    @Test
    void testNotEqualsLsquareBeginAGVDock() {
        AGVDock agv = new AGVDock("D1",new WarehouseID("1"), 1, 3, 1, 3, 1, 3, "l+");
        AGVDock agv1 = new AGVDock("D1",new WarehouseID("1"), 2, 3, 1, 3, 1, 3, "l+");
        Assertions.assertNotEquals(agv.getLsquareBeginAGVDock(), agv1.getLsquareBeginAGVDock() );
    }

    @Test
    void testNotEqualsWsquareBeginAGVDock() {
        AGVDock agv = new AGVDock("D1",new WarehouseID("1"), 1, 3, 1, 3, 1, 3, "l+");
        AGVDock agv1 = new AGVDock("D1",new WarehouseID("1"), 1, 5, 1, 3, 1, 3, "l+");
        Assertions.assertNotEquals(agv.getWsquareBeginAGVDock(), agv1.getWsquareBeginAGVDock() );
    }

    @Test
    void testNotEqualsLsquareEndAGVDock() {
        AGVDock agv = new AGVDock("D1",new WarehouseID("1"), 1, 3, 1, 3, 1, 3, "l+");
        AGVDock agv1 = new AGVDock("D1",new WarehouseID("1"), 1, 3, 5, 3, 1, 3, "l+");
        Assertions.assertNotEquals(agv.getLsquareEndAGVDock(), agv1.getLsquareEndAGVDock() );
    }

    @Test
    void testNotEqualsWsquareEndAGVDock() {
        AGVDock agv = new AGVDock("D1",new WarehouseID("1"), 1, 3, 1, 4, 1, 3, "l+");
        AGVDock agv1 = new AGVDock("D1",new WarehouseID("1"), 2, 3, 1, 3, 1, 3, "l+");
        Assertions.assertNotEquals(agv.getWsquareEndAGVDock(), agv1.getWsquareEndAGVDock() );
    }

    @Test
    void testNotEqualsLsquareDepthAGVDock() {
        AGVDock agv = new AGVDock("D1",new WarehouseID("1"), 1, 3, 1, 4, 1, 3, "l+");
        AGVDock agv1 = new AGVDock("D1",new WarehouseID("1"), 2, 3, 1, 3, 2, 3, "l+");
        Assertions.assertNotEquals(agv.getLsquareDepthAGVDock(), agv1.getLsquareDepthAGVDock() );
    }

    @Test
    void testNotEqualsWsquareDepthAGVDock() {
        AGVDock agv = new AGVDock("D1",new WarehouseID("1"), 1, 3, 1, 4, 1, 3, "l+");
        AGVDock agv1 = new AGVDock("D1",new WarehouseID("1"), 2, 3, 1, 3, 1, 6, "l+");
        Assertions.assertNotEquals(agv.getWsquareDepthAGVDock(), agv1.getWsquareDepthAGVDock() );
    }

    @Test
    void testNotEqualsAccessibilityAGVDock() {
        AGVDock agv = new AGVDock("D1",new WarehouseID("1"), 1, 3, 1, 4, 1, 3, "l+");
        AGVDock agv1 = new AGVDock("D1",new WarehouseID("1"), 2, 3, 1, 3, 1, 3, "l-");
        Assertions.assertNotEquals(agv.getAccessibility(), agv1.getAccessibility() );
    }
}