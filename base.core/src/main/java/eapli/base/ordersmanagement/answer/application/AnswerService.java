package eapli.base.ordersmanagement.answer.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.answer.domain.Answer;
import eapli.base.ordersmanagement.answer.repository.AnswerRepository;
import eapli.base.ordersmanagement.customer.domain.Customer;

import java.util.List;

public class AnswerService {

    private final AnswerRepository answerRepository = PersistenceContext.repositories().answer();

    public String universe(String id){
        List<Answer> customerList = answerRepository.universe(id);
        String size = String.valueOf(customerList.size());
        return size;
    }
}
