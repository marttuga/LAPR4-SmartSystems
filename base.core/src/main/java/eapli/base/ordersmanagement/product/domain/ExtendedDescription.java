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
public class ExtendedDescription implements ValueObject, Comparable<ExtendedDescription> {

    @Column(name = "extendedDescription")
    private String description;

    public ExtendedDescription(String description) throws IllegalAccessException {
        if(StringPredicates.isNullOrWhiteSpace(description)) {
            throw new IllegalAccessException("The extended description cannot be null or empty!");
        }
        if(description.length() > 100 && description.length() < 20){
            throw new IllegalArgumentException("The description should be with a minimum of 20 chars and 100 chars maximum.");
        }
        Pattern descRegerx = Pattern.compile("");
        this.description = description;
    }


    protected ExtendedDescription() {

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
    public int compareTo(ExtendedDescription o) {
        return 0;
    }
}