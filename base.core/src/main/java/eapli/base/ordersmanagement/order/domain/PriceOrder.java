package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Money;

import javax.persistence.*;


@Embeddable
public class PriceOrder  implements ValueObject {

    @Column(insertable = false,updatable = false)
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "priceMoneyAmount")),
            @AttributeOverride(name = "currency", column = @Column(name = "priceMoneyCurrency"))})
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
