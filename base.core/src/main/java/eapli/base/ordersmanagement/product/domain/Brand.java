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
public class Brand implements ValueObject, Comparable<Brand>  {


    @Column(name = "brandName")
    private String brandName;

    protected Brand(){
    }

    public Brand(String brandName) throws IllegalAccessException {
        if(StringPredicates.isNullOrWhiteSpace(brandName)) {
            throw new IllegalAccessException("The brand name cannot be null or empty!");
        }
        if(brandName.length() > 50){
            throw new IllegalArgumentException("The brand name should not have more than 50 characters.");
        }
        Pattern descRegerx = Pattern.compile("");
        this.brandName = brandName;
    }

    public String getBrandName() {
        return brandName;
    }

    @Override
    public String toString(){
        return brandName;
    }

    @Override
    public int hashCode() {
        return this.brandName.hashCode();
    }

    @Override
    public int compareTo(Brand o) {
        return brandName.compareTo(o.brandName);
    }
}
