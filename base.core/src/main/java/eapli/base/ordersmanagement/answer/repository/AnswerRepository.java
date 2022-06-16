package eapli.base.ordersmanagement.answer.repository;

import eapli.base.ordersmanagement.answer.domain.Answer;
import eapli.base.ordersmanagement.answer.domain.AnswerId;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.customer.domain.CustomerId;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;
import java.util.Map;

public interface AnswerRepository extends DomainRepository<AnswerId, Answer> {
    public List<Answer> universe(String id);

    public Map<String, String> answers();

}
