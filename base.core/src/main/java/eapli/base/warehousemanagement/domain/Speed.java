package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Speed implements ValueObject, Comparable<Speed> {

    private int speed;

    protected Speed() {
    }

    public Speed(int speed) {
        this.speed = speed;
    }

    @Override
    public int compareTo(Speed o) {
        return 0;
    }
}
