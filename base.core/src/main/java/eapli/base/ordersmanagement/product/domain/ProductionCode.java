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
public class ProductionCode implements ValueObject, Comparable<ProductionCode>  {


    @Column(name = "productionCode")
    private String productionCode;

    protected ProductionCode(){
    }

    public ProductionCode(String productionCode) throws IllegalAccessException {
        if(StringPredicates.isNullOrWhiteSpace(productionCode)) {
            throw new IllegalAccessException("The production code cannot be null or empty!");
        }
        if(productionCode.length() > 23){
            throw new IllegalArgumentException("The production code should not have more than 23 characters.");
        }
        Pattern descRegerx = Pattern.compile("");
        this.productionCode = productionCode;
    }

    @Override
    public String toString(){
        return productionCode;
    }

    @Override
    public int hashCode() {
        return this.productionCode.hashCode();
    }

    @Override
    public int compareTo(ProductionCode o) {
        return productionCode.compareTo(o.productionCode);
    }
}
