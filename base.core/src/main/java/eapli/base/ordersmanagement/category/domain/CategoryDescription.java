package eapli.base.ordersmanagement.category.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;

@Embeddable
public class CategoryDescription implements ValueObject, Comparable<CategoryDescription> {

    private String categoryDescription;

    protected CategoryDescription(){
    }

    public CategoryDescription(String categoryDescription) {
        if (StringPredicates.isNullOrEmpty(categoryDescription)||categoryDescription.length()<20||categoryDescription.length()>50) {
            throw new IllegalArgumentException(
                    "Category description should neither be null nor empty");
        }
        this.categoryDescription=categoryDescription;
    }

    public static CategoryDescription valueOf(String  categoryDescription){
        return new CategoryDescription(categoryDescription);
    }

    @Override
    public int compareTo(CategoryDescription o) {
        return 0;
    }
}
