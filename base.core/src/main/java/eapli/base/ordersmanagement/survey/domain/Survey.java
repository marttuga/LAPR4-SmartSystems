package eapli.base.ordersmanagement.survey.domain;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


@Entity
public class Survey implements AggregateRoot<AlphanumericCode> {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private AlphanumericCode alphanumericCode;

    @Embedded
    private SurveyDescription surveyDescription;

    @Embedded
    private SurveyPeriod surveyPeriod;

    @Lob
    @Basic(fetch=FetchType.EAGER)
    private byte[] surveyFile;

    @Embedded
    private SurveyRule surveyRule;
    @OneToMany
    private List<Customer> customers;

    protected Survey() {
    }


    public Survey(AlphanumericCode alphanumericCode, SurveyDescription surveyDescription, SurveyPeriod surveyPeriod, byte[] surveyFile, SurveyRule surveyRule, List<Customer> customers) {
        this.alphanumericCode = alphanumericCode;
        this.surveyDescription = surveyDescription;
        this.surveyPeriod = surveyPeriod;
        this.surveyFile = Arrays.copyOf(surveyFile, surveyFile.length);;
        this.surveyRule = surveyRule;
        this.customers = customers;
    }

    public SurveyDescription getSurveyDescription() {
        return surveyDescription;
    }

    public SurveyPeriod getSurveyPeriod() {
        return surveyPeriod;
    }

    public AlphanumericCode getAlphanumericCode() {
        return alphanumericCode;
    }

    public byte[] getSurveyFile() {
        return surveyFile;
    }

    public SurveyRule getSurveyRules() {
        return surveyRule;
    }


    public List<Customer> getCustomers() {
        return customers;
    }

    @Override
    public int compareTo(AlphanumericCode other) {
        return AggregateRoot.super.compareTo(other);
    }

    @Override
    public AlphanumericCode identity() {
        return this.alphanumericCode;
    }

    @Override
    public boolean hasIdentity(AlphanumericCode id) {
        return AggregateRoot.super.hasIdentity(id);
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

}
