package eapli.base.ordersmanagement.order.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class OrderIDTest {

    @Test
    void ensureIsNotNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new OrderID(null));
    }

    @Test
    void ensureIsNotEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new OrderID(""));
    }
    @Test
    void valueOf() {

        OrderID or = new OrderID("12");
        Assertions.assertEquals(OrderID.valueOf("12"),or );
    }

    @Test
    void testEquals() {
        OrderID o = new OrderID("12");
        OrderID or = new OrderID("12");
        Assertions.assertEquals(o, or);
    }

    @Test
    void testNotEquals() {
        OrderID o = new OrderID("1");
        OrderID or = new OrderID("12");
        Assertions.assertNotEquals(o, or);
    }

    @Test
    void testHashCode() {
        OrderID o = new OrderID("12");
        Assertions.assertEquals(1600, o.hashCode());
    }

    @Test
    void testToString() {
        OrderID o = new OrderID("12");
        Assertions.assertEquals("12", o.toString());
    }

    @Test
    void compareTo() {
        OrderID o = new OrderID("12");
        OrderID or = new OrderID("12");
        Assertions.assertEquals(0, o.compareTo(or));
    }
}