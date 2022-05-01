package eapli.base.ordersmanagement.product.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ExtendedDescriptionTest {

    @Test
    void valueOf() throws IllegalAccessException {
        ExtendedDescription extendedDescription = new ExtendedDescription("Extended Description");
        Assertions.assertEquals("Extended Description", extendedDescription.getDescription());
    }

    @Test
    void getDescription() throws IllegalAccessException {
        ExtendedDescription extendedDescription = new ExtendedDescription("Extended Description");
        Assertions.assertEquals("Extended Description", extendedDescription.getDescription());
    }
}
