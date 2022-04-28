package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Bin implements ValueObject, Comparable<Bin> {

    private int bin;

    public Bin(final String bin) {
        if (StringPredicates.isNullOrEmpty(bin)) {
            throw new IllegalArgumentException(
                    "Bin should neither be null nor empty");
        }
        this.bin = Integer.parseInt(bin);
    }

    protected Bin(){

    }

    public static Bin valueOf(final String bin) {
        return new Bin(bin);
    }

    @Override
    public String toString() {
        return "Bin{" +
                "bin=" + bin +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(bin);
    }

    @Override
    public int compareTo(Bin o) {
        return 0;
    }
}
