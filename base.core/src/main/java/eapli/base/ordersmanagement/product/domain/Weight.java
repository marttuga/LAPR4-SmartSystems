package eapli.base.ordersmanagement.product.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author João Tiago Araújo <<1200584@isep.ipp.pt>>
 */

@Embeddable
public class Weight implements ValueObject, Comparable<Weight>{

    @Column(insertable = false,updatable = false)
    private Double weight;

    protected Weight() {
    }

    public Weight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Weight{" +
                "weight=" + weight;
    }

    @Override
    public int hashCode() {
        return this.weight.hashCode();
    }

    @Override
    public int compareTo(Weight o) {
        return 0;
    }
}
