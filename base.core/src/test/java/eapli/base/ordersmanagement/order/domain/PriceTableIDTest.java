package eapli.base.ordersmanagement.order.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceTableIDTest {

    @Test
    void ensureIsNotNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PriceTableID(null));
    }

    @Test
    void ensureIsNotEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PriceTableID(""));
    }
    @Test
    void valueOf() {

        PriceTableID or = new PriceTableID("12");
        Assertions.assertEquals(PriceTableID.valueOf("12").toString(),or.toString() );
    }

    @Test
    void testEquals() {
        PriceTableID o = new PriceTableID("12");
        PriceTableID or = new PriceTableID("12");
        Assertions.assertEquals(o.toString(), or.toString());
    }

    @Test
    void testNotEquals() {
        PriceTableID o = new PriceTableID("1");
        PriceTableID or = new PriceTableID("12");
        Assertions.assertNotEquals(o, or);
    }

    @Test
    void testToString() {
        PriceTableID o = new PriceTableID("12");
        Assertions.assertEquals("12", o.toString());
    }

    @Test
    void compareTo() {
        PriceTableID o = new PriceTableID("12");
        PriceTableID or = new PriceTableID("12");
        Assertions.assertEquals(0, o.compareTo(or));
    }
}