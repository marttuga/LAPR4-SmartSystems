package eapli.base.ordersmanagement.category.domain;

import eapli.base.ordersmanagement.product.domain.Product;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Category implements Serializable {

    //NAO ESTA ACABADO E NAO TENHO A CERTEZA
    @Id
    private String category;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category")

    private Category subCategory;

    public Category(){};

    public Category(String category){
        this.category=category;
    }

    @Override
    public String toString(){
        return category;
    }
}