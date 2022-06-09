package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
public class Autonomy implements ValueObject, Comparable<Autonomy>{

    private int autonomy;

    protected Autonomy(){

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
    public int compareTo(Autonomy o) {
        return 0;
    }
}
