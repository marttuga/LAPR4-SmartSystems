package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Money;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;


@Embeddable
public class PriceOrder  implements ValueObject {

    private Money priceMoney;


    private ShippingCost shippingC;

    public PriceOrder() {
    }

    public PriceOrder(Money priceMoney, ShippingCost shippingC) {
        this.priceMoney = priceMoney;
        this.shippingC = shippingC;
    }

    @Override
    public String toString() {
        return "total price: \n"+ priceMoney;
    }
}
