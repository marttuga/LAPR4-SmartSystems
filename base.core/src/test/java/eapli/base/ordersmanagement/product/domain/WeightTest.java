package eapli.base.ordersmanagement.product.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeightTest {

    @Test
    void getWeight() {
        Weight weight = new Weight(20.0);
        Assertions.assertEquals(20.0, weight.getWeight());
    }

    @Test
    void valueOf() {
        Weight weight = new Weight(20.0);
        Assertions.assertEquals(20.0, weight.getWeight());
    }
}