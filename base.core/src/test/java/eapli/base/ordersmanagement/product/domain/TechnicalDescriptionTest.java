package eapli.base.ordersmanagement.product.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TechnicalDescriptionTest {

    @Test
    void getDescription() throws IllegalAccessException {
        TechnicalDescription technicalDescription = new TechnicalDescription("TechnicalDescription");
        Assertions.assertEquals("TechnicalDescription", technicalDescription.getDescription());
    }

    @Test
    void valueOf() throws IllegalAccessException {
        TechnicalDescription technicalDescription = new TechnicalDescription("TechnicalDescription");
        Assertions.assertEquals("TechnicalDescription", technicalDescription.getDescription());
    }
}