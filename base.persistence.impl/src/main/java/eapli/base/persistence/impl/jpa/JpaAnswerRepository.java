package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordersmanagement.answer.domain.Answer;
import eapli.base.ordersmanagement.answer.domain.AnswerId;
import eapli.base.ordersmanagement.answer.repository.AnswerRepository;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.customer.domain.CustomerId;
import eapli.base.ordersmanagement.customer.repositories.CustomerRepository;
import eapli.base.ordersmanagement.survey.domain.Survey;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

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
        Query q = entityManager().createQuery(" SELECT s.answers FROM Survey s WHERE s.id = :id");
        q.setParameter("id", id);
        return (q.getResultList());
    }
    //SELECT a.customer FROM Answer a WHERE a IN

    @Override
    public List<Answer> universeAnswers(String id){
    List<Answer> answerList = new ArrayList<>();
    TypedQuery<Answer> query = super.createQuery("SELECT DISTINCT s.answers FROM Survey s", Answer.class);
        for (Answer a: query.getResultList()) {
        answerList.add(a);
    }
        return answerList;
    }
}
