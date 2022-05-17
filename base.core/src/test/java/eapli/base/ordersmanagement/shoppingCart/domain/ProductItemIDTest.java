package eapli.base.ordersmanagement.shoppingCart.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductItemIDTest {

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
        ProductItemID or = new ProductItemID("12");
        Assertions.assertEquals(ProductItemID.valueOf("12").toString(),or.toString() );
    }

    @Test
    void testToString() {
        ProductItemID o = new ProductItemID("12");
        Assertions.assertEquals("12", o.toString());
    }

    @Test
    void compareTo() {
        ProductItemID o = new ProductItemID("12");
        ProductItemID or = new ProductItemID("12");
        Assertions.assertEquals(0, o.compareTo(or));
    }
}