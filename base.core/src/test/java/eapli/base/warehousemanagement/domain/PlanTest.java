package eapli.base.warehousemanagement.domain;

import eapli.base.ordersmanagement.order.domain.LineOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanTest {


    @Test
    void getLength() {
        Plan plan = new Plan(0, 15000, 100, "cm");
        Assertions.assertEquals(0, plan.getLength());
    }

    @Test
    void getWidth() {
        Plan plan = new Plan(20000, 15000, 100, "cm");
        Assertions.assertEquals(15000, plan.getWidth());
    }

    @Test
    void getSquare() {
        Plan plan = new Plan(20000, 15000, 100, "cm");
        Assertions.assertEquals(100, plan.getSquare());
    }

    @Test
    void getUnit() {
        Plan plan = new Plan(20000, 15000, 100, "cm");
        Assertions.assertEquals("cm", plan.getUnit());
    }

    @Test
    void testNotEqualsLength() {
        Plan p = new Plan(20000, 15000, 100, "cm");
        Plan p1 = new Plan(10000, 15000, 100, "cm");
        Assertions.assertNotEquals(p.getLength(), p1.getLength());
    }

    @Test
    void testNotEqualsWidth() {
        Plan p = new Plan(20000, 15000, 100, "cm");
        Plan p1 = new Plan(20000, 5000, 100, "cm");
        Assertions.assertNotEquals(p.getWidth(), p1.getWidth());
    }

    @Test
    void testNotEqualsSquare() {
        Plan p = new Plan(20000, 15000, 100, "cm");
        Plan p1 = new Plan(20000, 15000, 10, "cm");
        Assertions.assertNotEquals(p.getSquare(), p1.getSquare());
    }

    @Test
    void testNotEqualsUnit() {
        Plan p = new Plan(20000, 15000, 100, "cm");
        Plan p1 = new Plan(20000, 15000, 100, "m");
        Assertions.assertNotEquals(p.getUnit(), p1.getUnit());
    }
}