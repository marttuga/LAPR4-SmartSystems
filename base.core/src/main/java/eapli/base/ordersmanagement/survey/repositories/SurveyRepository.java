package eapli.base.ordersmanagement.survey.repositories;

import eapli.base.ordersmanagement.survey.domain.AlphanumericCode;
import eapli.base.ordersmanagement.survey.domain.Survey;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface SurveyRepository extends DomainRepository<AlphanumericCode,Survey> {
    public List<Survey> findAll();
}
