package eapli.base.ordersmanagement.category.domain;

import eapli.base.ordersmanagement.customer.domain.CustomerFirstName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCodeTest {
    @Test
    void TestCategoryCode() {
        Assertions.assertNotNull(new CategoryCode("1234567"));
    }

    @Test
    void ensureCategoryCodeIsNotNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CategoryCode(null));
    }

    @Test
    void ensureCategoryCodeIsNotEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CategoryCode(""));
    }

    @Test
    void ensureCategoryCodeIsNotMoreThan10() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CategoryCode("12345678901"));
    }

}