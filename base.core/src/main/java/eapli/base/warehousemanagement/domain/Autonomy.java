package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
public class Autonomy implements ValueObject, Comparable<Autonomy>{

    private int autonomy;

    public Autonomy(){

    }

    public Autonomy(int autonomy) throws IllegalAccessException {
        if(0 < autonomy && autonomy > 100){
            throw new IllegalArgumentException("The autonomy should be from 0 to 100.");
        }
        Pattern descRegerx = Pattern.compile("");
        this.autonomy = autonomy;
    }

    public static Autonomy valueOf(final int autonomy) throws IllegalAccessException {
        return new Autonomy(autonomy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autonomy autonomy1 = (Autonomy) o;
        return autonomy == autonomy1.autonomy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(autonomy);
    }

    @Override
    public String toString() {
        return "Autonomy{" +
                "autonomy=" + autonomy +
                '}';
    }

    @Override
    public int compareTo(Autonomy o) {
        return 0;
    }


    public int discharching(int speed, double discharchingRate){
        int result = (int) ( speed * discharchingRate);
        return  result;
    }
}
