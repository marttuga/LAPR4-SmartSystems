package eapli.base.ordersmanagement.product.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

/**
 *
 * @author João Tiago Araújo <<1200584@isep.ipp.pt>>
 */

@Embeddable
public class Weight implements ValueObject, Comparable<Weight>{

    @Column(insertable = false,updatable = false)
    private double weight;

    protected Weight() {
    }

    public Weight(double weight) {
        this.weight = weight;
    }

    public Weight(String weight) {

    }

    @Override
    public String toString() {
        return "Weight{" +
                "weight=" + weight;
    }

    public static Weight valueOf(final String weight) throws IllegalAccessException {
        return new Weight(weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight);
    }

    @Override
    public int compareTo(Weight o) {
        return 0;
    }
}
