package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.*;

@Embeddable
public class PaymentMethod implements ValueObject {

    static enum Method {
        PAY_PAL,
        MBWAY,
        CREDIT_CARD,
        BANK_TRANSFERS,
        VENMO,
    }
    private Method method;
    private String payment;

    public PaymentMethod() {
    }

    public PaymentMethod(Method method, String payment) {
        this.method = method;
        this.payment = payment;
    }

    public PaymentMethod(Method method) {
        this.method = method;
    }
}


