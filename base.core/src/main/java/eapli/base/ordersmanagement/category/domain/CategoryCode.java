package eapli.base.ordersmanagement.category.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class CategoryCode implements ValueObject, Comparable<CategoryCode> {
    private String categoryCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryCode that = (CategoryCode) o;
        return Objects.equals(categoryCode, that.categoryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryCode);
    }

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
    public String toString() {
        return this.categoryCode;
    }



    @Override
    public int compareTo(CategoryCode o) {
        return 0;
    }
}
