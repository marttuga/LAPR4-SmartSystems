package eapli.base.ordersmanagement.order.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.*;


public enum PaymentMethod implements ValueObject {


        PAY_PAL,
        MBWAY,
        CREDIT_CARD,
        BANK_TRANSFERS,
        VENMO,


}


