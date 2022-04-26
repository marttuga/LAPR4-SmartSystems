package eapli.base.ordersmanagement.category.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class CategoryCode implements ValueObject, Comparable<CategoryCode> {
    private String categoryCode;

    public CategoryCode(final String code) {
        if (StringPredicates.isNullOrEmpty(code)) {
            throw new IllegalArgumentException(
                    "Category alpha numeric code should neither be null nor empty");
        }
        this.categoryCode = code;
    }

    protected CategoryCode() {
    }

    public static CategoryCode valueOf(final String c) {
        return new CategoryCode(c);
    }

    @Override
    public int hashCode() {
        return this.categoryCode.hashCode();
    }

    @Override
    public String toString() {
        return this.categoryCode;
    }

    @Override
    public int compareTo(final CategoryCode o) {
        return categoryCode.compareTo(o.categoryCode);
    }
}
