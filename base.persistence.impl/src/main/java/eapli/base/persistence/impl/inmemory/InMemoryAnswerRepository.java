package eapli.base.persistence.impl.inmemory;

import eapli.base.ordersmanagement.answer.domain.Answer;
import eapli.base.ordersmanagement.answer.domain.AnswerId;
import eapli.base.ordersmanagement.answer.repository.AnswerRepository;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.List;

public class InMemoryAnswerRepository extends InMemoryDomainRepository<Answer, AnswerId> implements AnswerRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public List<Customer> universe(String id){ return universe(id);  }
}
