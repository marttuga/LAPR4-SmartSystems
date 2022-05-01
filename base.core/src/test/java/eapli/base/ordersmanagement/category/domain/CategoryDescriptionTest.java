package eapli.base.ordersmanagement.category.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryDescriptionTest {

    @Test
    void TestCategoryDescription() {
        Assertions.assertNotNull(new CategoryDescription("High heels strapless open"));
    }

    @Test
    void ensureCategoryDescriptionIsNotNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CategoryDescription(null));
    }

    @Test
    void ensureCategoryDescriptionIsNotEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CategoryDescription(""));
    }

    @Test
    void ensureCategoryCodeIsNotMoreThan50() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CategoryCode("ivwrciyreibceirbciebrcibericb3irbcibtiyb4ibiybt4ibcibiyb4ibyi4rbf"));
    }
    @Test
    void ensureCategoryCodeIsNotLessThan20() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CategoryCode("high heels"));
    }

}