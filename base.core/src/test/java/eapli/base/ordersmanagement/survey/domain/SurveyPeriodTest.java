package eapli.base.ordersmanagement.survey.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SurveyPeriodTest {
    @Test
    void testAlphanumericCode() {
        Assertions.assertNotNull(new AlphanumericCode("123456"));
    }

    @Test
    void ensureAlphanumericCodeIsNotNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new AlphanumericCode(null));
    }
}