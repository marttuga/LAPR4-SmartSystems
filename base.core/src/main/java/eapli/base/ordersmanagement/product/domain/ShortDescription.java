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
public class ShortDescription implements ValueObject, Comparable<ShortDescription> {

    @Column(name = "shortDescription")
    private String description;

    public ShortDescription(String description) throws IllegalAccessException {
        if(StringPredicates.isNullOrWhiteSpace(description)) {
            throw new IllegalAccessException("The short description cannot be null or empty!");
        }
        if(description.length() > 30){
            throw new IllegalArgumentException("The description should not have more than 30 characters.");
        }
        Pattern descRegerx = Pattern.compile("");
        this.description = description;
    }


    protected ShortDescription() {

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
    public int compareTo(ShortDescription o) {
        return 0;
    }
}
