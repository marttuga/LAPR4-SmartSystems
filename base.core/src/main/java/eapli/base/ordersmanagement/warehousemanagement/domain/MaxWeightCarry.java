package eapli.base.ordersmanagement.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class MaxWeightCarry implements ValueObject, Comparable<MaxWeightCarry> {

    private Double maxWeight;

    protected MaxWeightCarry() {
    }

    @Override
    public String toString() {
        return "MaxWeightCarry{" +
                "maxWeight=" + maxWeight +
                '}';
    }

    @Override
    public int hashCode() {
        return this.maxWeight.hashCode();
    }

    @Override
    public int compareTo(MaxWeightCarry o) {
        return maxWeight.compareTo(o.maxWeight);
    }
}