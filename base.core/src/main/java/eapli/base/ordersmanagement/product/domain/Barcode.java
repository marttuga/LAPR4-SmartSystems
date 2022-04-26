package eapli.base.ordersmanagement.product.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

/**
 *
 * @author João Tiago Araújo <<1200584@isep.ipp.pt>>
 */

@Embeddable
public class Barcode implements ValueObject, Comparable<Barcode>{

    @Column(name = "barcode")
    public String barcode;

    public Barcode(String barcode) throws IllegalAccessException {
        if(StringPredicates.isNullOrWhiteSpace(barcode)) {
            throw new IllegalAccessException("The barcode cannot be null or empty!");
        }
        if(barcode.length() != 13 ){
            throw new IllegalArgumentException("The barcode EAN-13 must have 13 digits!");
        }
        Pattern descRegerx = Pattern.compile("");
        this.barcode = barcode;
    }

    protected Barcode() {

    }

    @Override
    public String toString() {
        return barcode;
    }

    @Override
    public int hashCode() {
        return this.barcode.hashCode();
    }

    @Override
    public int compareTo(Barcode o) {
        return 0;
    }
}
