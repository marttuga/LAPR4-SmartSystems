package eapli.base.ordersmanagement.customer.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.time.util.CalendarPredicates;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.util.Calendar;

@Embeddable
public class BirthDate implements ValueObject {

    private Calendar birthDate;


    public BirthDate(){
    }

     public BirthDate(Calendar birthDate){
         Preconditions.ensure(CalendarPredicates.isBeforeNow(birthDate), "Invalid birthdate - Must be before today!");
         this.birthDate=birthDate;
     }
}
