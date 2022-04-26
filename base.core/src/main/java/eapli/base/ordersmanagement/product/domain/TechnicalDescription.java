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
public class TechnicalDescription implements ValueObject, Comparable<TechnicalDescription> {

    @Column(name = "technicalDescription")
    private String description;

    public TechnicalDescription(String description) throws IllegalAccessException {
        if(StringPredicates.isNullOrWhiteSpace(description)) {
            throw new IllegalAccessException("The technical description cannot be null or empty!");
        }
        Pattern descRegerx = Pattern.compile("");
        this.description = description;
    }


    protected TechnicalDescription() {

    }

    @Override
    public String toString(){
        return description;
    }

    @Override
    public int hashCode() {
        return this.description.hashCode();
    }

    @Override
    public int compareTo(TechnicalDescription o) {
        return 0;
    }
}