package eapli.base.ordersmanagement.product.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void getAisleId() throws IllegalAccessException {
        ProductionCode productionCode = new ProductionCode("Prod123");
        Assertions.assertEquals("Prod123", productionCode.getProductionCode());
    }

    @Test
    void getRowId() {
    }

    @Test
    void getShelfId() {
    }

    @Test
    void getCategoryCode() {
    }

    @Test
    void valueOfAisleId() {
    }

    @Test
    void valueOfRowId() {
    }

    @Test
    void valueOfShelfId() {
    }
}