package eapli.base.ordersmanagement.survey.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class SurveyCode implements ValueObject, Comparable<SurveyCode> {
    private String coding;


    
    public SurveyCode(final String alphaNumericCode) {
        if (StringPredicates.isNullOrEmpty(alphaNumericCode)) {
            throw new IllegalArgumentException(
                    "Survey alpha numeric code should neither be null nor empty");
        }
        this.coding = alphaNumericCode;
    }

    protected SurveyCode() {
    }

    public static SurveyCode valueOf(final String surveyCode) {
        return new SurveyCode(surveyCode);
    }

    @Override
    public int hashCode() {
        return this.coding.hashCode();
    }

    @Override
    public String toString() {
        return this.coding;
    }

    @Override
    public int compareTo(final SurveyCode o) {
        return coding.compareTo(o.coding);
    }
}
