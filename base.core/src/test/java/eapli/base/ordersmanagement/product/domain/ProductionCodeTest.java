package eapli.base.ordersmanagement.product.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductionCodeTest {

    @Test
    void valueOf() throws IllegalAccessException {
        ProductionCode productionCode = new ProductionCode("Prod123");
        Assertions.assertEquals("Prod123", productionCode.getProductionCode());
    }

    @Test
    void getProductionCode() throws IllegalAccessException {
        ProductionCode productionCode = new ProductionCode("Prod123");
        Assertions.assertEquals("Prod123", productionCode.getProductionCode());
    }
}