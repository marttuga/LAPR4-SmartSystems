package eapli.base.ordersmanagement.customer.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerVATIdentifierTest {

    @Test
    void testVatIdentifier() {
        Assertions.assertNotNull(new CustomerVATIdentifier("1234567"));
    }

    @Test
    void ensureVatIdentifierIsNotNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CustomerVATIdentifier(null));
    }

    @Test
    void ensureVatIdentifierIsNotEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CustomerVATIdentifier(""));
    }

    @Test
    void ensureLastNameIsNotEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CustomerVATIdentifier("12345678"));
    }
}