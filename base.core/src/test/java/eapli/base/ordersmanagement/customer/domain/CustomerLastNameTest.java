package eapli.base.ordersmanagement.customer.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerLastNameTest {

    @Test
    void testLastName() {
        Assertions.assertNotNull(new CustomerFirstName("John"));
    }

    @Test
    void ensureLastNameIsNotNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CustomerLastName(null));
    }

    @Test
    void ensureLastNameIsNotEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CustomerLastName(""));
    }
}