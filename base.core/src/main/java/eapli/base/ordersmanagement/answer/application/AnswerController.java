package eapli.base.ordersmanagement.answer.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.answer.domain.Answer;
import eapli.base.ordersmanagement.answer.domain.AnswerId;
import eapli.base.ordersmanagement.answer.repository.AnswerRepository;
import eapli.base.ordersmanagement.customer.applicaion.RegisterCustomerController;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.customer.repositories.CustomerRepository;
import eapli.base.ordersmanagement.order.application.OrderService;
import eapli.base.ordersmanagement.order.domain.*;
import eapli.base.ordersmanagement.order.repositories.LineOrderRepository;
import eapli.base.ordersmanagement.survey.domain.Survey;
import eapli.base.ordersmanagement.survey.repositories.SurveyRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.hibernate.loader.collection.OneToManyJoinWalker;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class AnswerController {
    private final RegisterCustomerController customerController = new RegisterCustomerController();
    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();
    private final AnswerRepository answerRepository = PersistenceContext.repositories().answer();
    private final TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();
    private final SurveyRepository repo = PersistenceContext.repositories().survey();
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();

    public Answer registerAnswer(AnswerId id, Customer customer, Map<String, String> m) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.CLIENT_USER);


        Answer answer = new Answer(id, customer, m);


        return answerRepository.save(answer);
    }

    public void saveSurveyAnswered(Survey survey, List<Answer> answers) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.CLIENT_USER);
        txCtx.beginTransaction();
        assert survey.getAnswers() != null;
        for (Answer a : answers) {
            survey.getAnswers().add(a);
        }
        saveAnswers(answers);
        this.repo.save(survey);

        txCtx.commit();

    }

    public void saveAnswers(List<Answer> answers) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.CLIENT_USER);
        for (Answer a : answers) {
            this.answerRepository.save(a);
        }

    }

    public void saveSurveyRemoveCustomers(Survey survey, List<Customer> customers, Customer c) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.CLIENT_USER);
        txCtx.beginTransaction();
        assert survey.getCustomers() != null;
        customers.removeIf(a -> a.getCustomerEmailAddress().toString().equals(c.getCustomerEmailAddress().toString()));
        saveCustomers(customers);
        this.repo.save(survey);

        txCtx.commit();

    }

    public void saveCustomers(List<Customer> c) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.CLIENT_USER);
        for (Customer a : c) {
            this.customerRepository.save(a);
        }

    }
}
