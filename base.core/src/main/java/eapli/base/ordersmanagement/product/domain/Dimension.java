package eapli.base.ordersmanagement.product.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import java.util.Objects;

/**
 *
 * @author João Tiago Araújo <<1200584@isep.ipp.pt>>
 */

@Embeddable
public class Dimension implements ValueObject, Comparable<Dimension> {

    private Double width;

    private Double height;

    private Double length;

    public Dimension(Double width, Double height, Double length) {
        this.width = width;
        this.height = height;
        this.length = length;
    }

    protected Dimension() {

    }

/*    public Double getWidth() {
        return width;
    }

    public Double getHeight() {
        return height;
    }

    public Double getLength() {
        return length;
    }*/

    public Double getVolume(Double width, Double height, Double length){
        return (width * height * length);
    }

    @Override
    public String toString() {
        return "Dimension{" +
                "width=" + width +
                ", height=" + height +
                ", length=" + length +
                '}';
    }

/*
    @Override
    public int hashCode() {
        return Objects.hash(getWidth(), getHeight(), getLength());
    }
*/
    @Override
    public int compareTo(Dimension o) {
        return 0;
    }
}
