package eapli.base.ordersmanagement.category.domain;

import eapli.base.ordersmanagement.product.domain.Product;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Category implements AggregateRoot<CategoryCode> {

    //NAO ESTA ACABADO E NAO TENHO A CERTEZA
    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private CategoryCode categoryCode;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Product> productList;

    public Category() {}

    public static CategoryCode valueOf(final String orderIdentification) {
        return new CategoryCode(orderIdentification);
    }

    @Override
    public int hashCode() {
        return this.categoryCode.hashCode();
    }

    @Override
    public String toString() {
        return "Category:" +
                "categoryCode=" + categoryCode +
                ", productList=" + productList ;
    }

    @Override
    public int compareTo(CategoryCode other) {
        return AggregateRoot.super.compareTo(other);
    }

    @Override
    public CategoryCode identity() {
        return this.categoryCode;
    }

    @Override
    public boolean hasIdentity(CategoryCode id) {
        return AggregateRoot.super.hasIdentity(id);
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }
}