package eapli.base.ordersmanagement.customer.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CustomerPostalAddressTest {

    @Test
    void testAddress() {
        Assertions.assertNotNull(new CustomerPostalAddress("sunset boulevard",45,"Los Angeles","USA",90210));
    }

}
