package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.survey.domain.AlphanumericCode;
import eapli.base.ordersmanagement.survey.domain.Survey;
import eapli.base.ordersmanagement.survey.repositories.SurveyRepository;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.Status;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


public class JpaSurveyRepository extends JpaAutoTxRepository<Survey, AlphanumericCode, AlphanumericCode> implements SurveyRepository {
    public JpaSurveyRepository(TransactionalContext autoTx) {
        super(autoTx, "alphanumericCode");
    }
    public JpaSurveyRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "alphanumericCode");
    }

    @Override
    public List<Survey> findAll() {
        List<Survey> surveyList = new ArrayList<>();
        TypedQuery<Survey> query = super.createQuery("SELECT DISTINCT s FROM Survey s", Survey.class);
        for (Survey s: query.getResultList()) {
            surveyList.add(s);
        }
        return surveyList;
    }






}
