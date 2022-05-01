package eapli.base.ordersmanagement.order.domain;

import eapli.base.ordersmanagement.product.domain.Weight;
import eapli.framework.general.domain.model.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceOrderTest {

    @Test
    void testToString() {
        Money shippingCost = Money.euros(3);
        Weight weight = new Weight(2);
        ShippingCost s = new ShippingCost(shippingCost, SalesRegion.AUSTRIA, weight);
        Money priceMoney= Money.euros(3);
        PriceOrder p= new PriceOrder(priceMoney,s);
        String expected = "total price: \n"+ priceMoney;
        Assertions.assertEquals(expected, p.toString());

    }

}