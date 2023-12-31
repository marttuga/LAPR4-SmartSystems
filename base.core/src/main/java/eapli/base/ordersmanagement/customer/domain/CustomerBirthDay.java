package eapli.base.ordersmanagement.customer.domain;

import eapli.base.utilitarianClasses.AgeCalculator;
import eapli.framework.domain.model.ValueObject;
import javax.persistence.Embeddable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Embeddable
public class CustomerBirthDay implements ValueObject {

    private Date birthDate;


    protected CustomerBirthDay(){
    }

    public CustomerBirthDay(final Date dateBirht){
        if (dateBirht == null)  {
            throw new IllegalArgumentException(
                    "Age attributes should not be null nor empty");
        }
        // TODO validate invariants, i.e., mecanographic number regular
        // expression

        this.birthDate = dateBirht;
    }

    @Override
    public String toString() {
        return String.valueOf(birthDate) ;
    }

    public int getAge(){
        LocalDate bd = this.birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate cr = LocalDate.now();

        if (bd != null) {
            return Period.between(bd, cr).getYears();
        } else {
            return 0;
        }
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public static CustomerBirthDay valueOf(final Date birthDate){
        return new CustomerBirthDay(birthDate);
    }
}
