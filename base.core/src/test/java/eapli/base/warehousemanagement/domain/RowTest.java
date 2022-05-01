package eapli.base.warehousemanagement.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RowTest {

    @Test
    void valueOf() {
        Row row = new Row(1,5,1,7,1);
        Assertions.assertEquals(1,row.getRowId() );
    }

    @Test
    void testNotEqualsLsquareBegin() {
        Row r = new Row(1,5,1,7,1);
        Row r1 = new Row(1,7,1,7,1);
        Assertions.assertNotEquals(r.getLsquareBegin(), r1.getLsquareBegin());
    }

    @Test
    void testNotEqualsWsquareBegin() {
        Row r = new Row(1,5,1,7,1);
        Row r1 = new Row(1,5,3,7,1);
        Assertions.assertNotEquals(r.getWsquareBegin(), r1.getWsquareBegin());
    }

    @Test
    void testNotEqualsLsquareEnd() {
        Row r = new Row(1,5,1,10,1);
        Row r1 = new Row(1,5,1,7,1);
        Assertions.assertNotEquals(r.getLsquareEnd(), r1.getLsquareEnd());
    }

    @Test
    void testNotEqualsWsquareEnd() {
        Row r = new Row(1,5,1,7,1);
        Row r1 = new Row(1,5,3,7,3);
        Assertions.assertNotEquals(r.getWsquareEnd(), r1.getWsquareEnd());
    }
}