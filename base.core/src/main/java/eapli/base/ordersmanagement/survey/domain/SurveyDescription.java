package eapli.base.ordersmanagement.survey.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class SurveyDescription implements ValueObject, Comparable<SurveyDescription> {
    private String surveyDescription;


    public SurveyDescription(final String surveyDescription) {
        if (StringPredicates.isNullOrEmpty(surveyDescription)) {
            throw new IllegalArgumentException(
                    "Survey description should neither be null nor empty");
        }
        this.surveyDescription = surveyDescription;
    }

    protected SurveyDescription() {
    }

    public static SurveyDescription valueOf(final String surveyDescription) {
        return new SurveyDescription(surveyDescription);
    }

    @Override
    public int hashCode() {
        return this.surveyDescription.hashCode();
    }

    @Override
    public String toString() {
        return this.surveyDescription;
    }

    @Override
    public int compareTo(final SurveyDescription o) {
        return surveyDescription.compareTo(o.surveyDescription);
    }
}
