package eapli.base.warehousemanagement.domain;

import eapli.base.ordersmanagement.order.domain.OrderID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseIDTest {

    @Test
    void ensureIsNotNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new WarehouseID(null));
    }

    @Test
    void ensureIsNotEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new WarehouseID(""));
    }

    @Test
    void valueOf() {

        WarehouseID war = new WarehouseID("2");
        Assertions.assertEquals(WarehouseID.valueOf("2"),war );
    }

    @Test
    void testEquals() {
        WarehouseID w = new WarehouseID("3");
        WarehouseID wa = new WarehouseID("3");
        Assertions.assertEquals(w, wa);
    }

    @Test
    void testNotEquals() {
        WarehouseID w = new WarehouseID("3");
        WarehouseID wa = new WarehouseID("8");
        Assertions.assertNotEquals(w, wa);
    }

    @Test
    void testHashCode() {
        WarehouseID w = new WarehouseID("2");
        Assertions.assertEquals(81, w.hashCode());
    }

    @Test
    void testToString() {
        WarehouseID o = new WarehouseID("2");
        Assertions.assertEquals("WarehouseID: 2", o.toString());
    }

    @Test
    void compareTo() {
        WarehouseID w = new WarehouseID("2");
        WarehouseID wa = new WarehouseID("2");
        Assertions.assertEquals(0, w.compareTo(wa));
    }
}