package eapli.base.ordersmanagement.order.domain;

import eapli.base.ordersmanagement.product.domain.Weight;
import eapli.framework.general.domain.model.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShippingCostTest {

    @Test
    void shippingMoney() {
        Money shippingCost = Money.euros(3);
        Weight weight = new Weight(2);
        ShippingCost s = new ShippingCost(shippingCost, SalesRegion.AUSTRIA, weight);
        Assertions.assertEquals(shippingCost, s.shippingMoney());

    }
}