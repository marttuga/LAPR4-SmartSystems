package eapli.base.ordersmanagement.product.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShortDescriptionTest {

    @Test
    void getDescription() throws IllegalAccessException {
        ShortDescription shortDescription = new ShortDescription("ShortDescription");
        Assertions.assertEquals("ShortDescription", shortDescription.getDescription());
    }

    @Test
    void valueOf() throws IllegalAccessException {
        ShortDescription shortDescription = new ShortDescription("ShortDescription");
        Assertions.assertEquals("ShortDescription", shortDescription.getDescription());
    }
}