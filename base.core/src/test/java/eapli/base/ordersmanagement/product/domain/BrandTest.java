package eapli.base.ordersmanagement.product.domain;

import eapli.base.ordersmanagement.order.domain.LineOrderID;
import eapli.base.ordersmanagement.order.domain.OrderID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandTest {


    @Test
    void getBrandName() throws IllegalAccessException {
        Brand b= new Brand("oi");
        Assertions.assertEquals("oi",b.getBrandName() );

    }
    @Test
    void valueOf() throws IllegalAccessException {

        Brand b= new Brand("oi");
        Assertions.assertEquals(Brand.valueOf("oi").toString(),b.toString() );
    }

    @Test
    void testEquals() throws IllegalAccessException {
        Brand b= new Brand("oi");
        Brand bo= new Brand("oi");
        Assertions.assertEquals(bo.toString(), b.toString());
    }

    @Test
    void testNotEquals() throws IllegalAccessException {
        Brand b= new Brand("oi");
        Brand bo= new Brand("o");
        Assertions.assertNotEquals(bo, b);
    }

    @Test
    void testHashCode() throws IllegalAccessException {
        Brand bo= new Brand("o");
        Assertions.assertEquals(111, bo.hashCode());
    }

    @Test
    void testToString() throws IllegalAccessException {
        Brand bo= new Brand("o");
        Assertions.assertEquals("o", bo.toString());
    }

    @Test
    void compareTo() throws IllegalAccessException {
        Brand bo= new Brand("o");
        Brand o= new Brand("o");
        Assertions.assertEquals(0, o.compareTo(bo));
    }
}