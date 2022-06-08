package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordersmanagement.survey.domain.AlphanumericCode;
import eapli.base.ordersmanagement.survey.domain.Survey;
import eapli.base.ordersmanagement.survey.repositories.SurveyRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;


public class JpaSurveyRepository extends JpaAutoTxRepository<Survey, AlphanumericCode, AlphanumericCode> implements SurveyRepository {
    public JpaSurveyRepository(TransactionalContext autoTx) {
        super(autoTx, "alphanumericCode");
    }
    public JpaSurveyRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "alphanumericCode");
    }

}
