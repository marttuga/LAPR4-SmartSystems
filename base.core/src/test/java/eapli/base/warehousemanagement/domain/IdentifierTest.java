package eapli.base.warehousemanagement.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentifierTest {

    @Test
    void ensureIsNotNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Identifier(null));
    }

    @Test
    void ensureIsNotEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Identifier(""));
    }



    @Test
    void testNotEquals() {
        Identifier id = new Identifier("3");
        Identifier id1 = new Identifier("8");
        Assertions.assertNotEquals(id, id1);
    }

    @Test
    void testHashCode() {
        Identifier i = new Identifier("2");
        Assertions.assertEquals(494317290, i.hashCode());
    }

    @Test
    void testToString() {
        Identifier i = new Identifier(" 2");
        Assertions.assertEquals("Identifier: 2", i.toString());
    }

    @Test
    void compareTo() {
        Identifier id = new Identifier("2");
        Identifier id1 = new Identifier("2");
        Assertions.assertEquals(0, id.compareTo(id1));
    }

}