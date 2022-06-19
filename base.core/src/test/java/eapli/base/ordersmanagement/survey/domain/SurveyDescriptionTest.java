package eapli.base.ordersmanagement.survey.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SurveyDescriptionTest {

    @Test
    void testSurveyDescription() {
        Assertions.assertNotNull(new SurveyDescription("123456"));
    }

    @Test
    void ensureSurveyDescriptionIsNotNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SurveyDescription(null));
    }
}