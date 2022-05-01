package eapli.base.ordersmanagement.order.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineOrderIDTest {

    @Test
    void ensureIsNotNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new LineOrderID(null));
    }

    @Test
    void ensureIsNotEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new LineOrderID(""));
    }

    @Test
    void valueOf() {

        LineOrderID or = new LineOrderID("12");
        Assertions.assertEquals(LineOrderID.valueOf("12"), or);
    }

    @Test
    void testEquals() {
        LineOrderID o = new LineOrderID("12");
        LineOrderID or = new LineOrderID("12");
        Assertions.assertEquals(o, or);
    }


    @Test
    void testNotEquals() {
        LineOrderID o = new LineOrderID("1");
        LineOrderID or = new LineOrderID("12");
        Assertions.assertNotEquals(o, or);
    }

    @Test
    void testHashCode() {
        LineOrderID o = new LineOrderID("12");
        Assertions.assertEquals(1600, o.hashCode());
    }

    @Test
    void testToString() {
        LineOrderID o = new LineOrderID("12");
        Assertions.assertEquals("12", o.toString());
    }

    @Test
    void compareTo() {
        LineOrderID o = new LineOrderID("12");
        LineOrderID or = new LineOrderID("12");
        Assertions.assertEquals(0, o.compareTo(or));
    }
}