package eapli.base.ordersmanagement.survey.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class SurveyPeriod implements ValueObject, Comparable<SurveyPeriod>{

    private int surveyPeriod;


    protected SurveyPeriod() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SurveyPeriod that = (SurveyPeriod) o;
        return surveyPeriod == that.surveyPeriod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(surveyPeriod);
    }

    public int getSurveyPeriod() {
        return surveyPeriod;
    }

    @Override
    public String toString() {
        return "SurveyPeriod{" +
                "surveyPeriod=" + surveyPeriod +
                '}';
    }


    @Override
    public int compareTo(SurveyPeriod o) {
        return 0;
    }
}
