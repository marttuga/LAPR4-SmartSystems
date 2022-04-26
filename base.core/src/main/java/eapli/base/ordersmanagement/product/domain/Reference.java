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
public class Reference implements ValueObject, Comparable<Reference>  {


    @Column(name = "reference")
    private String reference;

    protected Reference(){
    }

    public Reference(String reference) throws IllegalAccessException {
        if(StringPredicates.isNullOrWhiteSpace(reference)) {
            throw new IllegalAccessException("The reference cannot be null or empty!");
        }
        if(reference.length() > 23){
            throw new IllegalArgumentException("The reference should not have more than 23 characters.");
        }
        Pattern descRegerx = Pattern.compile("");
        this.reference = reference;
    }

    @Override
    public String toString(){
        return reference;
    }

    @Override
    public int hashCode() {
        return this.reference.hashCode();
    }

    @Override
    public int compareTo(Reference o) {
        return reference.compareTo(o.reference);
    }
}
