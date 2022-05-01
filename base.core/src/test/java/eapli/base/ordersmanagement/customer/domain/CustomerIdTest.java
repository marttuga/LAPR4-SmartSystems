package eapli.base.ordersmanagement.customer.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerIdTest {

    @Test
    void testCustomerId() {
        Assertions.assertNotNull(new CustomerId("2hs7g37s"));
    }

    @Test
    void ensureCustomerIdIsNotNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CustomerId(null));
    }
}