package eapli.base.ordersmanagement.product.domain;

import eapli.framework.general.domain.model.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductPriceDetailTest {

    @Test
    void valueOf() {
        Money price = Money.euros(20);
        ProductPriceDetail priceDetail = new ProductPriceDetail(price);
        Assertions.assertEquals(Money.euros(20), priceDetail.getPrice());
    }

    @Test
    void getPrice() {
        Money price = Money.euros(20);
        ProductPriceDetail priceDetail = new ProductPriceDetail(price);
        Assertions.assertEquals(Money.euros(20), priceDetail.getPrice());

    }
}