package eapli.base.ordersmanagement.order.domain;

import eapli.base.ordersmanagement.product.domain.Weight;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Money;

import javax.persistence.*;

@Embeddable
public class ShippingCost implements ValueObject {

    @Column(insertable = false,updatable = false)
    @AttributeOverride(name = "amount", column = @Column(name = "shippingMoney"))
    @AttributeOverride(name = "currency", column = @Column(name = "shippingMoney"))
    private Money shippingMoney;
    private SalesRegion salesReg;
    private Weight w;

    public ShippingCost() {
    }

    public ShippingCost(Money shippingMoney, SalesRegion salesReg, Weight w) {
        this.shippingMoney = shippingMoney;
        this.salesReg = salesReg;
        this.w = w;
    }
}
