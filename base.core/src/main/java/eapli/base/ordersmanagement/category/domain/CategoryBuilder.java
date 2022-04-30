package eapli.base.ordersmanagement.category.domain;

import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.framework.domain.model.DomainFactory;

public class CategoryBuilder implements DomainFactory<Category> {

    private Category theCategory;
    private CategoryCode categoryCode;
    private CategoryDescription categoryDescription;

    public CategoryBuilder withCategoryCode(final CategoryCode code){
        this.categoryCode=code;
        return this;
    }

    public CategoryBuilder withCategoryCode(final String code){
        this.categoryCode= new CategoryCode(code);
        return this;
    }

    public CategoryBuilder withCategoryDescription(final CategoryDescription description){
        this.categoryDescription=description;
        return this;
    }

    public CategoryBuilder withCategoryDescription(final String description){
        this.categoryDescription= new CategoryDescription(description);
        return this;
    }

    private Category buildOrThrow() {
        if (theCategory != null) {
            return theCategory;
        } else if (categoryCode != null && categoryDescription != null) {
                theCategory = new Category(categoryCode, categoryDescription);
            return theCategory;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public Category build() {
        final Category ret = buildOrThrow();
        // make sure we will create a new instance if someone reuses this builder and do not change
        // the previously built dish.
        theCategory = null;
        return ret;
    }
}
