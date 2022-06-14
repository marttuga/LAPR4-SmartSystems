package eapli.base.ordersmanagement.answer.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.answer.repository.AnswerRepository;
import eapli.base.ordersmanagement.customer.domain.Customer;

import java.util.List;

public class AnswerService {

    private final AnswerRepository answerRepository = PersistenceContext.repositories().answer();

    public int universe(String id){
        List<Customer> customerList = answerRepository.universe(id);
        return customerList.size();
    }
}
