package eapli.base.ordersmanagement.product.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 *
 * @author João Tiago Araújo <<1200584@isep.ipp.pt>>
 */

@Embeddable
public class UniqueInternalCode implements ValueObject, Comparable<UniqueInternalCode>{


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniqueInternalCode that = (UniqueInternalCode) o;
        return Objects.equals(uniqueInternalCode, that.uniqueInternalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueInternalCode);
    }

    @Column(name = "uniqueInternalCode")
    private String uniqueInternalCode;


    public UniqueInternalCode(String uniqueInternalCode) throws IllegalAccessException {
        if(StringPredicates.isNullOrWhiteSpace(uniqueInternalCode)) {
            throw new IllegalAccessException("The production code cannot be null or empty!");
        }
        if(uniqueInternalCode.length() > 23){
            throw new IllegalArgumentException("The production code should not have more than 23 characters.");
        }
        Pattern descRegerx = Pattern.compile("");
        this.uniqueInternalCode = uniqueInternalCode;
    }

    protected UniqueInternalCode() {
    }

    public static UniqueInternalCode valueOf(final String internalCode) throws IllegalAccessException {
        return new UniqueInternalCode(internalCode);
    }


    @Override
    public String toString() {
        return this.uniqueInternalCode;
    }



    @Override
    public int compareTo(UniqueInternalCode o) {
        return 0;
    }
}

