package eapli.base.ordersmanagement.survey.domain;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class AlphanumericCodeTest {

    @Test
    void testAlphanumericCode() {
        new AlphanumericCode("123456");
    }

    @Test
    void ensureAlphanumericCodeIsNotNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new AlphanumericCode(null));
    }
}