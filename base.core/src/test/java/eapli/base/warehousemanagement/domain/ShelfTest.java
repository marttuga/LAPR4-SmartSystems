package eapli.base.warehousemanagement.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShelfTest {

    @Test
    void getShelfId() {
        Shelf shelf = new Shelf(1, 4);
        Assertions.assertEquals(1,shelf.getShelfId() );
    }

    @Test
    void getNrShelves() {
        Shelf shelf = new Shelf(1, 4);
        Assertions.assertEquals(4,shelf.getNrShelves() );
    }

    @Test
    void testNotEqualsShelfId() {
        Shelf s = new Shelf(1, 4);
        Shelf s1 = new Shelf(2, 4);
        Assertions.assertNotEquals(s.getShelfId(),s1.getShelfId() );
    }

    @Test
    void testNotEqualsNrShelves() {
        Shelf s = new Shelf(1, 4);
        Shelf s1 = new Shelf(1, 10);
        Assertions.assertNotEquals(s.getNrShelves(),s1.getNrShelves() );
    }
}