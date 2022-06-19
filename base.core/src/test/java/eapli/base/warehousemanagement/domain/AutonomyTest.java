package eapli.base.warehousemanagement.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class AutonomyTest {

    @Test
    void compareTo() throws IllegalAccessException {
        Autonomy a1 = new Autonomy(100);
        Autonomy a2 = new Autonomy(100);
        Assertions.assertEquals(0, a1.compareTo(a2));
    }

    @Test
    void testToString() throws IllegalAccessException {
        Autonomy a = new Autonomy(100);
        Assertions.assertEquals("Autonomy: 100", a.toString());
    }

    @Test
    void ensureAutonomyIsNotEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Autonomy());
    }

    @Test
    void ensureAutonomyIsBetween0And100() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Autonomy(100));
    }
}