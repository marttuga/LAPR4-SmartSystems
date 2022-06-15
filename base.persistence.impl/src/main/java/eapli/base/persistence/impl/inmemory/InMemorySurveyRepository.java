package eapli.base.persistence.impl.inmemory;

import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.survey.domain.AlphanumericCode;
import eapli.base.ordersmanagement.survey.domain.Survey;
import eapli.base.ordersmanagement.survey.repositories.SurveyRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.List;

public class InMemorySurveyRepository extends InMemoryDomainRepository<Survey, AlphanumericCode> implements SurveyRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public List<Survey> findAll() {
        return findAll();
    }

    @Override
    public Survey findByID(String alphanumericCode) {
        return null;
    }


}

