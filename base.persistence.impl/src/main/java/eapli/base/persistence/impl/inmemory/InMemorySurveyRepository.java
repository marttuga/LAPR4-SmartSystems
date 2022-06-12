package eapli.base.persistence.impl.inmemory;

import eapli.base.ordersmanagement.survey.domain.AlphanumericCode;
import eapli.base.ordersmanagement.survey.domain.Survey;
import eapli.base.ordersmanagement.survey.repositories.SurveyRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemorySurveyRepository extends InMemoryDomainRepository<Survey, AlphanumericCode> implements SurveyRepository {
    static {
        InMemoryInitializer.init();
    }
}
