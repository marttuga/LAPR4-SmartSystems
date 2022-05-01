package eapli.base.ordersmanagement.product.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReferenceTest {

    @Test
    void valueOf() throws IllegalAccessException {
        Reference reference = new Reference("Reference");
        Assertions.assertEquals("Reference", reference.getReference());
    }

    @Test
    void getReference() throws IllegalAccessException {
        Reference reference = new Reference("Reference");
        Assertions.assertEquals("Reference", reference.getReference());
    }
}