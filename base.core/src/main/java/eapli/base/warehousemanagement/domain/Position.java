package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Position implements ValueObject, Comparable<Position>{

    private int x;
    private int y;

    public Position (int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected Position() {

    }

    @Override
    public int compareTo(Position o) {
        if(o.x == this.x && o.y == this.y) {
            return 1;
        }
        return 0;
    }
}
