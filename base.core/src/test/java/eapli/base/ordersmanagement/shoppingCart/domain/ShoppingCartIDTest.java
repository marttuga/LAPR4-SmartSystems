package eapli.base.ordersmanagement.shoppingCart.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartIDTest {

    @Test
    void ensureIsNotNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ProductItemID(null));
    }

    @Test
    void ensureIsNotEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ProductItemID(""));
    }
    @Test
    void valueOf() {
        ShoppingCartID or = new ShoppingCartID("12");
        Assertions.assertEquals(ShoppingCartID.valueOf("12").toString(),or.toString() );
    }

    @Test
    void testToString() {
        ShoppingCartID o = new ShoppingCartID("12");
        Assertions.assertEquals("12", o.toString());
    }

    @Test
    void compareTo() {
        ShoppingCartID o = new ShoppingCartID("12");
        ShoppingCartID or = new ShoppingCartID("12");
        Assertions.assertEquals(0, o.compareTo(or));
    }
}