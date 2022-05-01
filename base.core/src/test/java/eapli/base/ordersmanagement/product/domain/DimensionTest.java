package eapli.base.ordersmanagement.product.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DimensionTest {

    @Test
    void width() {
        Dimension dimension = new Dimension(1.1,1.2,1.3);
        Assertions.assertEquals(1.1, dimension.width());
    }

    @Test
    void height() {
        Dimension dimension = new Dimension(1.1,1.2,1.3);
        Assertions.assertEquals(1.2, dimension.height());
    }

    @Test
    void length() {
        Dimension dimension = new Dimension(1.1,1.2,1.3);
        Assertions.assertEquals(1.3, dimension.length());
    }

    @Test
    void valueOf() {
        Dimension dimension = new Dimension(1.1,1.2,1.3);
        Assertions.assertEquals(1.1, dimension.width());
    }

}