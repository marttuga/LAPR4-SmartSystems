package eapli.base.ordersmanagement.survey.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class SurveyCode implements ValueObject, Comparable<SurveyCode> {
    private String surveyCode;


    
    public SurveyCode(final String alphaNumericCode) {
        if (StringPredicates.isNullOrEmpty(alphaNumericCode)) {
            throw new IllegalArgumentException(
                    "Survey alpha numeric code should neither be null nor empty");
        }
        this.surveyCode = alphaNumericCode;
    }

    protected SurveyCode() {
    }

    public static SurveyCode valueOf(final String surveyCode) {
        return new SurveyCode(surveyCode);
    }

    @Override
    public int hashCode() {
        return this.surveyCode.hashCode();
    }

    @Override
    public String toString() {
        return this.surveyCode;
    }

    @Override
    public int compareTo(final SurveyCode o) {
        return surveyCode.compareTo(o.surveyCode);
    }
}
