package eapli.base.ordersmanagement.customer.domain;

import eapli.base.Utils.AgeCalculator;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.time.util.CalendarPredicates;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.util.Calendar;
import java.util.Date;

@Embeddable
public class CustomerBirthDay implements ValueObject {

    private Date birthDate;


    public CustomerBirthDay(){
    }

     public CustomerBirthDay(final Date dateBirht){
         if (dateBirht == null||getAge(dateBirht)<18)  {
             throw new IllegalArgumentException(
                     "Age attributes should neither be null nor empty and you should have at least 18 ");
         }
         // TODO validate invariants, i.e., mecanographic number regular
         // expression

         this.birthDate = dateBirht;
     }

    public static int getAge(Date date){
        return AgeCalculator.getCalculateAge(date);
    }
}
