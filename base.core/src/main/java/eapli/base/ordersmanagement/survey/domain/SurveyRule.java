package eapli.base.ordersmanagement.survey.domain;


import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.*;

@Embeddable
public class SurveyRule implements ValueObject {

    private int ruleAge;

    private String ruleGender;


    protected SurveyRule() {
    }


    public String getRuleGender() {
        return ruleGender;
    }

    public void setRuleGender(String ruleGender) {
        this.ruleGender = ruleGender;
    }

    public int getRuleAge() {
        return ruleAge;
    }

    public void setRuleAge(int ruleAge) {
        this.ruleAge = ruleAge;
    }

    public SurveyRule(int ruleAge) {
        if ((StringPredicates.isNullOrEmpty(String.valueOf(ruleAge)) || String.valueOf(ruleAge).length()>2 || ruleAge<16 ||ruleAge>100)) {
            throw new IllegalArgumentException("Age should be a realistic number");
        }
        this.ruleAge = ruleAge;
    }

    public SurveyRule(String ruleGender) {
        if (StringPredicates.isNullOrEmpty(ruleGender) || !ruleGender.equals("FEMALE") &&!ruleGender.equals("MALE") && !ruleGender.equals("OTHER")) {
            throw new IllegalArgumentException("Enter one of the specified");
        }
        this.ruleGender = ruleGender;
    }

    public SurveyRule(int ruleAge, String ruleGender) {
        if ((StringPredicates.isNullOrEmpty(String.valueOf(ruleAge)) || String.valueOf(ruleAge).length()>2 || ruleAge<16 ||ruleAge>100)) {
            throw new IllegalArgumentException("Age should be a realistic number");
        }
        this.ruleAge = ruleAge;
        if (StringPredicates.isNullOrEmpty(ruleGender) || !ruleGender.equals("FEMALE") &&!ruleGender.equals("MALE") && !ruleGender.equals("OTHER")) {
            throw new IllegalArgumentException("Enter one of the specified");
        }
        this.ruleGender = ruleGender;
    }

    @Override
    public String toString() {
        return "Rule:" +
                "/ birthDay:" + ruleAge +
                "/ gender:" + ruleGender;
    }
}
