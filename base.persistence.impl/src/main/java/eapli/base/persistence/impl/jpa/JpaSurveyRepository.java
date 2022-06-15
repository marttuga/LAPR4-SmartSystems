package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordersmanagement.survey.domain.AlphanumericCode;
import eapli.base.ordersmanagement.survey.domain.Survey;
import eapli.base.ordersmanagement.survey.repositories.SurveyRepository;
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
        TypedQuery<Survey> query = super.createQuery("SELECT DISTINCT c FROM Survey c", Survey.class);
        return new ArrayList<>(query.getResultList());
    }
    @Override
    public Survey findByID(String alphanumericCode) {
        Query q = entityManager().createQuery("SELECT ord FROM Survey ord " +
                " WHERE ord.alphanumericCode.alphanumericCode = :alphanumericCode");
        q.setParameter("alphanumericCode", alphanumericCode);
        return (Survey) q.getSingleResult();
    }

}
