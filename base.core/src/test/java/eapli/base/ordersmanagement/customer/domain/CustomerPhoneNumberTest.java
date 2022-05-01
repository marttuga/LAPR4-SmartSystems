package eapli.base.ordersmanagement.customer.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerPhoneNumberTest {

    @Test
    void testPhoneNumber() {
        Assertions.assertNotNull(new CustomerFirstName("917147649"));
    }

    @Test
    void ensurePhoneNumberIsNotNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CustomerPhoneNumber(null));
    }

    @Test
    void ensurePhoneNumberIsNotEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CustomerPhoneNumber(""));
    }
}