package eapli.base.ordersmanagement.survey.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class RuleAge implements ValueObject {

    private String age;

    protected RuleAge() {
    }

    public RuleAge(String age) {

        if (StringPredicates.isNullOrEmpty(age) || age.length()>2 || Integer.parseInt(age)<16 ||Integer.parseInt(age)>100) {
            throw new IllegalArgumentException("Age should be a realistic number");
        }
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public static RuleAge valueOf(final String s) {
        return new RuleAge(s);
    }

    @Override
    public String toString() {
        return "RuleAge:" + age ;
    }
}
