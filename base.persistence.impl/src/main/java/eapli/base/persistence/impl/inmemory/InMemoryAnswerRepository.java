package eapli.base.persistence.impl.inmemory;

import eapli.base.ordersmanagement.answer.domain.Answer;
import eapli.base.ordersmanagement.answer.domain.AnswerId;
import eapli.base.ordersmanagement.answer.repository.AnswerRepository;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.List;
import java.util.Map;

public class InMemoryAnswerRepository extends InMemoryDomainRepository<Answer, AnswerId> implements AnswerRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public List<Answer> universe(String id){ return universe(id);  }

    @Override
    public Map<String, String> answers(){ return answers(); }

}
