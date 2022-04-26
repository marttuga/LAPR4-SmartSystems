package eapli.base.ordersmanagement.survey.domain;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
public class Survey implements AggregateRoot<SurveyCode> {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private SurveyCode surveyCode;


    @Override
    public int compareTo(SurveyCode other) {
        return AggregateRoot.super.compareTo(other);
    }

    @Override
    public SurveyCode identity() {
        return this.surveyCode;
    }

    @Override
    public boolean hasIdentity(SurveyCode id) {
        return AggregateRoot.super.hasIdentity(id);
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    public SurveyCode surveyCode() {
        return identity();
    }

    public SurveyCode getSurveyCode() {
        return surveyCode;
    }
}
