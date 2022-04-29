package eapli.base.ordersmanagement.category.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;


import javax.persistence.*;

@Entity
public class Category implements AggregateRoot<CategoryCode>, Comparable<CategoryCode> {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private CategoryCode categoryCode;
    private CategoryDescription categoryDescription;

    protected Category() {
    }

    public Category(CategoryCode categoryCode, CategoryDescription categoryDescription) {
        this.categoryCode = categoryCode;
        this.categoryDescription = categoryDescription;
    }

    public static CategoryCode valueOf(final String orderIdentification) {
        return new CategoryCode(orderIdentification);
    }

    @Override
    public int hashCode() {
        return this.categoryCode.hashCode();
    }

    @Override
    public String toString() {
        return "Category: " +
                " categoryCode=" + categoryCode +
                ", description='" + categoryDescription ;
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

    public CategoryCode getCategoryCode() {
        return categoryCode;
    }

    public CategoryDescription getDescription() {
        return categoryDescription;
    }
}