package eapli.base.ordersmanagement.order.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceTableTest {

    @Test
    void sameAs() {
        PriceTable p=new PriceTable(SalesRegion.AUSTRIA);
        PriceTable pO=new PriceTable(SalesRegion.AUSTRIA);
        assertTrue( p.sameAs(pO));
    }

    @Test
    void compareTo() {
        PriceTableID p=new PriceTableID("r");
        PriceTableID pO=new PriceTableID("r");
        Assertions.assertEquals(0, p.compareTo(pO));

    }

    @Test
    void identity() {
    }

    @Test
    void getPriceTableID() {
        PriceTableID p=new PriceTableID("r");
        PriceTable pi= new PriceTable(p,SalesRegion.AUSTRIA);
        Assertions.assertEquals("r",pi.getPriceTableID().toString() );
    }

    @Test
    void getsRegion() {
        PriceTable p= new PriceTable(SalesRegion.AUSTRIA);
        Assertions.assertEquals(SalesRegion.AUSTRIA, p.getsRegion());

    }

    @Test
    void hasIdentity() {
    }
}