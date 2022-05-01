package eapli.base.ordersmanagement.product.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniqueInternalCodeTest {

    @Test
    void valueOf() throws IllegalAccessException {
        UniqueInternalCode uniqueInternalCode = new UniqueInternalCode("12345");
        Assertions.assertEquals("12345", uniqueInternalCode.getUniqueInternalCode());
    }

    @Test
    void getUniqueInternalCode() throws IllegalAccessException {
        UniqueInternalCode uniqueInternalCode = new UniqueInternalCode("12345");
        Assertions.assertEquals("12345", uniqueInternalCode.getUniqueInternalCode());
    }
}