package eapli.base.ordersmanagement.answer.repository;

import eapli.base.ordersmanagement.answer.domain.Answer;
import eapli.base.ordersmanagement.answer.domain.AnswerId;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.customer.domain.CustomerId;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface AnswerRepository extends DomainRepository<AnswerId, Answer> {
    public List<Customer> universe(String id);
}
