package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class MaxWeightCarry implements ValueObject, Comparable<MaxWeightCarry> {

    private int maxWeight;

    protected MaxWeightCarry() {
    }

    public MaxWeightCarry(int maxWeight) {
    }

    public static MaxWeightCarry valueOf(final int maxWeight) {
        return new MaxWeightCarry(maxWeight);
    }

    @Override
    public String toString() {
        return "MaxWeightCarry{" +
                "maxWeight=" + maxWeight +
                '}';
    }


    @Override
    public int compareTo(MaxWeightCarry o) {
        return 0;
    }
}