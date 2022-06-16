package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
public class Speed implements ValueObject, Comparable<Speed> {

    private int speed;

    protected Speed() {
    }
    public Speed(int speed) {
        if(0 < speed && speed > 10){
            throw new IllegalArgumentException("The speed should be from 0 to 10.");
        }
        Pattern descRegerx = Pattern.compile("");
        this.speed = speed;
    }

    @Override
    public int compareTo(Speed o) {
        return 0;
    }
}
