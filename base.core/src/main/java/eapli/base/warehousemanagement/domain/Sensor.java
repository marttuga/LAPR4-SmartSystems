package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;

public class Sensor implements ValueObject, Comparable<Position>{

    private String id;
    private int x;
    private int y;

    public Sensor (String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Position o) {
        return 0;
    }
}
