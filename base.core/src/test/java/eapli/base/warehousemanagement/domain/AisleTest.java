package eapli.base.warehousemanagement.domain;

import eapli.base.ordersmanagement.order.domain.OrderID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AisleTest {

    @Test
    void valueOf() {
        Aisle aisle = new Aisle(1,5,1,16,1,16,1,"w+");
        Assertions.assertEquals(1,aisle.getAisleId() );
    }

    @Test
    void testNotEqualsId(){
        Aisle a = new Aisle(1,6,1,7,1,20,1,"w-");
        Aisle a1 = new Aisle(2,6,1,7,1,20,1,"w-");
        Assertions.assertNotEquals(a.getAisleId(), a1.getAisleId());
    }

    @Test
    void testNotEqualsLSquareBegin(){
        Aisle a = new Aisle(1,6,1,7,1,20,1,"w-");
        Aisle a1 = new Aisle(2,2,1,7,1,20,1,"w-");
        Assertions.assertNotEquals(a.getLsquareBegin(), a1.getLsquareBegin());
    }

    @Test
    void testNotEqualsWSquareBegin(){
        Aisle a = new Aisle(1,6,1,7,1,20,1,"w-");
        Aisle a1 = new Aisle(2,6,3,7,1,20,1,"w-");
        Assertions.assertNotEquals(a.getWsquareBegin(), a1.getWsquareBegin());
    }

    @Test
    void testNotEqualsLSquareEnd(){
        Aisle a = new Aisle(1,6,1,7,1,20,1,"w-");
        Aisle a1 = new Aisle(2,6,1,10,1,20,1,"w-");
        Assertions.assertNotEquals(a.getLsquareEnd(), a1.getLsquareEnd());
    }

    @Test
    void testNotEqualsWSquareEnd(){
        Aisle a = new Aisle(1,6,1,7,1,20,1,"w-");
        Aisle a1 = new Aisle(2,6,1,7,3,20,1,"w-");
        Assertions.assertNotEquals(a.getWsquareEnd(), a1.getWsquareEnd());
    }

    @Test
    void testNotEqualsBeginDepth(){
        Aisle a = new Aisle(1,6,1,7,1,20,1,"w-");
        Aisle a1 = new Aisle(2,6,1,7,1,12,1,"w-");
        Assertions.assertNotEquals(a.getBeginDepth(), a1.getBeginDepth());
    }

    @Test
    void testNotEqualsEndDepth(){
        Aisle a = new Aisle(1,6,1,7,1,20,1,"w-");
        Aisle a1 = new Aisle(2,6,1,7,1,20,5,"w-");
        Assertions.assertNotEquals(a.getEndDepth(), a1.getEndDepth());
    }

    @Test
    void testNotEqualsAccessibility(){
        Aisle a = new Aisle(1,6,1,7,1,20,1,"w-");
        Aisle a1 = new Aisle(2,6,1,7,1,20,1,"w+");
        Assertions.assertNotEquals(a.getAccessibility(), a1.getAccessibility());
    }

}