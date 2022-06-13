package eapli.base.ordersmanagement.survey.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class SurveyRuleID implements ValueObject,  Comparable<SurveyRuleID>{

    private String ruleID;

    public SurveyRuleID(final String id) {
        if (StringPredicates.isNullOrEmpty(id)) {
            throw new IllegalArgumentException(
                    "SurveyRuleID should neither be null nor empty");
        }
        this.ruleID = id;
    }


    protected SurveyRuleID() {
    }

    public static SurveyRuleID valueOf(final String id) {
        return new SurveyRuleID(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SurveyRuleID lorderID1 = (SurveyRuleID) o;
        return Objects.equals(ruleID, lorderID1.ruleID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ruleID);
    }

    @Override
    public String toString() {
        return this.ruleID;
    }

    @Override
    public int compareTo(SurveyRuleID o) {
        return 0;
    }
}

