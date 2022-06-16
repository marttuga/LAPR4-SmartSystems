package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordersmanagement.answer.domain.Answer;
import eapli.base.ordersmanagement.answer.domain.AnswerId;
import eapli.base.ordersmanagement.answer.repository.AnswerRepository;
import eapli.base.ordersmanagement.survey.domain.Survey;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JpaAnswerRepository extends JpaAutoTxRepository<Answer, AnswerId, AnswerId> implements AnswerRepository {
    public JpaAnswerRepository(TransactionalContext autoTx) {
        super(autoTx, "answerId");
    }

    public JpaAnswerRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "answerId");
    }

    @Override
    public List<Answer> universe(String id){
        Query q = entityManager().createQuery(" SELECT s.answers FROM Survey s WHERE s.id.alphanumericCode = :id");
        q.setParameter("id", id);
        return q.getResultList();
    }

    public Map<String, String> answers(){
        Query q = entityManager().createQuery("SELECT a.every_Answer FROM Answer a");
        return (Map<String, String>) q.getResultList();
    }
}
