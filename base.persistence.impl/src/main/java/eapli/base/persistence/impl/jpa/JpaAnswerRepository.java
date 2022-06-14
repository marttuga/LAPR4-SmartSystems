package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordersmanagement.answer.domain.Answer;
import eapli.base.ordersmanagement.answer.domain.AnswerId;
import eapli.base.ordersmanagement.answer.repository.AnswerRepository;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.customer.domain.CustomerId;
import eapli.base.ordersmanagement.customer.repositories.CustomerRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.Query;
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
    public List<Customer> universe(String id){
        Query q = entityManager().createQuery("SELECT c FROM Customer c WHERE c IN (SELECT a.customer FROM Answer a WHERE a.id IN ( SELECT s FROM Survey s WHERE s.id = :id))");
        q.setParameter("id", id);
        return (q.getResultList());
    }
}
