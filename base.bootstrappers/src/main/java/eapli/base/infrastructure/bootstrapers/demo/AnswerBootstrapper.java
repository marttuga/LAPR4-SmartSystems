package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.answer.domain.Answer;
import eapli.base.ordersmanagement.answer.domain.AnswerId;
import eapli.base.ordersmanagement.answer.repository.AnswerRepository;
import eapli.base.ordersmanagement.customer.domain.*;
import eapli.base.ordersmanagement.customer.repositories.CustomerRepository;
import eapli.base.ordersmanagement.survey.domain.AlphanumericCode;
import eapli.base.ordersmanagement.survey.domain.SurveyDescription;
import eapli.base.ordersmanagement.survey.domain.SurveyPeriod;
import eapli.base.ordersmanagement.survey.domain.SurveyRule;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.domain.repositories.TransactionalContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnswerBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            AnswerBootstrapper.class);

    private final AnswerRepository answerRepository = PersistenceContext.repositories().answer();

    private final CustomerRepository r = PersistenceContext.repositories().customers();

    private final TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();
    @Override
    public boolean execute() {
        Customer customer = new Customer(CustomerId.valueOf("87654321") , CustomerFirstName.valueOf("Gabi"), CustomerLastName.valueOf("Hall"), CustomerEmailAdress.valueOf("gabi@gmail.com"), CustomerPhoneNumber.valueOf("910477203"), CustomerVATIdentifier.valueOf("9263748"));
        List<Customer> customers = new ArrayList<>();
        List<Answer>answerList = new ArrayList<>();
        customers.add(customer);
        Map<String, String> answers  = new HashMap<>();
        answers.put("1","A");
        answers.put("2","C");
        answers.put("3","A");
        answers.put("4","D");
        createAnswer("231", customer,answers);
        return true;
    }

    private void createAnswer(String id, Customer customer, Map<String,String> answers) {

        try {
            txCtx.beginTransaction();
            saveCustomers(customer);
            Answer answer1 = new Answer(new AnswerId(id), customer, answers);
            this.answerRepository.save(answer1);
            txCtx.commit();
        } catch (final ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", id);
            LOGGER.trace("Assuming existing record", e);
        }
    }

    public void saveCustomers( Customer customer) {
        this.r.save(customer);
    }
}
