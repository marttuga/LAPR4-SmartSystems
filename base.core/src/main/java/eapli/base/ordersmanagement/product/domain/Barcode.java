package eapli.base.ordersmanagement.product.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author João Tiago Araújo <<1200584@isep.ipp.pt>>
 */

@Embeddable
public class Barcode implements ValueObject, Comparable<Barcode>{

    @Column(name = "barcode")
    public String barcode;

    public Barcode (String barcode){

        Pattern codeRegex = Pattern.compile("^[0-9]{13}$");
        Matcher matcher = codeRegex.matcher(barcode);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Barcode must be written in EAN-13 type");
        }

        this.barcode = barcode;
    }

    protected Barcode() {

    }

    public String getBarcode() {
        return barcode;
    }

    public static Barcode valueOf(final String barcode) throws IllegalAccessException {
        return new Barcode(barcode);
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
