package eapli.base.ordersmanagement.answer.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class AnswerIdTest {

    @Test
    void testAnswerId() {
        new AnswerId("123456");
    }

    @Test
    void ensureAnswerIdIsNotNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new AnswerId(null));
    }

}