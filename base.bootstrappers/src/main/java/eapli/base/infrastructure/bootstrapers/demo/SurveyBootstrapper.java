package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.ordersmanagement.answer.domain.Answer;
import eapli.base.ordersmanagement.answer.domain.AnswerId;
import eapli.base.ordersmanagement.customer.applicaion.RegisterCustomerController;
import eapli.base.ordersmanagement.customer.domain.*;
import eapli.base.ordersmanagement.survey.application.CreateNewQuestionnaireController;
import eapli.base.ordersmanagement.survey.domain.AlphanumericCode;
import eapli.base.ordersmanagement.survey.domain.SurveyDescription;
import eapli.base.ordersmanagement.survey.domain.SurveyPeriod;
import eapli.base.ordersmanagement.survey.domain.SurveyRule;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;

public class SurveyBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(
            SurveyBootstrapper.class);

    private final CreateNewQuestionnaireController controller = new CreateNewQuestionnaireController();

    private final RegisterCustomerController customerController = new RegisterCustomerController();

    @Override
    public boolean execute() {
        Set<CustomerPostalAddress> postalAddress = new HashSet<>();
        CustomerPostalAddress customerPostalAddress = new CustomerPostalAddress("Cornelia",22,"San Francisco", "USA",90213);
        postalAddress.add(customerPostalAddress);
        Customer customer = new Customer(CustomerId.valueOf("87654321") , CustomerFirstName.valueOf("Gabi"), CustomerLastName.valueOf("Hall"), CustomerEmailAdress.valueOf("gabi@gmail.com"), CustomerPhoneNumber.valueOf("910477203"), CustomerVATIdentifier.valueOf("9263748"), CustomerBirthDay.valueOf(new Date(2001, 3, 2)),CustomerGender.valueOf("FEMALE"),postalAddress);
        List<Customer>customers = new ArrayList<>();
        List<Answer>answerList = new ArrayList<>();
        customers.add(customer);

        Map<String, String> answers  = new HashMap<>();
        answers.put("1","A");
        answers.put("2","C");
        answers.put("3","A");
        answers.put("4","D");
        Answer answer = new Answer( new AnswerId("123"),customer, answers);
        answerList.add(answer);
        File file = new File("base.core/src/main/java/eapli/base/surveys/questionnaire.txt");
        byte[] surveyFile = new byte[(int) file.length()];
        createSurvey("321", "wtv", 10, surveyFile,16,"FEMALE", customers, answerList);
        return true;
    }

    private void createSurvey(String id, String description, int period, byte[] surveyfile, int  surveyruleA,String surveyruleG, List<Customer> customers, List<Answer> answers) {

        try {
            controller.createSurveyAnswered(new AlphanumericCode(id),new SurveyDescription(description), new SurveyPeriod(period), surveyfile, new SurveyRule(surveyruleA,surveyruleG), customers, answers);
        } catch (final ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", id);
            LOGGER.trace("Assuming existing record", e);
        }
    }
}
