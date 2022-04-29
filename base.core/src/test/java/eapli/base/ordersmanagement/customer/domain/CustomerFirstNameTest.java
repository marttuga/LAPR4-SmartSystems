package eapli.base.ordersmanagement.customer.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerFirstNameTest {

    @Test
    void testFirstName() {
        Assertions.assertNotNull(new CustomerFirstName("John"));
    }

    @Test
    void ensureFirstNameIsNotNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CustomerFirstName(null));
    }

    @Test
    void ensureFirstNameIsNotEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CustomerFirstName(""));
    }

}