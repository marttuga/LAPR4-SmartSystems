package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.util.Collections;
import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
public class Autonomy implements ValueObject, Comparable<Autonomy>{

    private int autonomy;

    public Autonomy(){

    }

    public Autonomy(int autonomy) {
        /*
        if(StringPredicates.isNullOrWhiteSpace(String.valueOf(autonomy))) {
            throw new IllegalAccessException("The autonomy cannot be null or empty!");
        }


        if(autonomy < 0 || autonomy > 100){
            throw new IllegalArgumentException("The autonomy should be from 0 to 100.");
        }
        */

        Pattern descRegerx = Pattern.compile("");
        this.autonomy = autonomy;
    }

    public static Autonomy valueOf(final int autonomy) throws IllegalAccessException {
        return new Autonomy(autonomy);
    }

    public int getAutonomy() {
        return autonomy;
    }

    public void setAutonomy(int autonomy) {
        this.autonomy = autonomy;
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
