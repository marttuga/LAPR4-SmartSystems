package eapli.base.ordersmanagement.product.domain;


import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Brand implements ValueObject, Comparable<Brand>  {

    @Column(name = "brandName")
    private String brandName;

    public Brand(){
    }

    public Brand(String brand){
        this.brandName = brand;
    }

    @Override
    public String toString(){
        return brandName;
    }


    @Override
    public int compareTo(Brand o) {
        return brandName.compareTo(o.brandName);
    }
}
