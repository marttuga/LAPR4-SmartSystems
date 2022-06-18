package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.ordersmanagement.answer.application.AnswerController;
import eapli.base.ordersmanagement.answer.domain.Answer;
import eapli.base.ordersmanagement.answer.domain.AnswerId;
import eapli.base.ordersmanagement.customer.applicaion.RegisterCustomerController;
import eapli.base.ordersmanagement.customer.domain.*;
import eapli.base.ordersmanagement.survey.application.CreateNewQuestionnaireController;
import eapli.base.ordersmanagement.survey.application.QuestionnaireAnswerController;
import eapli.base.ordersmanagement.survey.domain.*;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class SurveyBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(
            SurveyBootstrapper.class);

    private final CreateNewQuestionnaireController controller = new CreateNewQuestionnaireController();

    private final QuestionnaireAnswerController qaController = new QuestionnaireAnswerController();
    private final AnswerController answerController = new AnswerController();
    private final RegisterCustomerController customerController = new RegisterCustomerController();

    @Override
    public boolean execute() {
        Set<CustomerPostalAddress> postalAddress = new HashSet<>();
        CustomerPostalAddress customerPostalAddress = new CustomerPostalAddress("Cornelia", 22, "San Francisco", "USA", 90213);
        postalAddress.add(customerPostalAddress);
        Customer customer = new Customer(CustomerId.valueOf("87654321"), CustomerFirstName.valueOf("Gabi"), CustomerLastName.valueOf("Hall"), CustomerEmailAdress.valueOf("gabi@gmail.com"), CustomerPhoneNumber.valueOf("910477203"), CustomerVATIdentifier.valueOf("9263748"), CustomerBirthDay.valueOf(new Date(Calendar.YEAR + 100, 3, 2)), CustomerGender.valueOf("FEMALE"), postalAddress);
        List<Customer> customers = new ArrayList<>();
        List<Answer> answerList = new ArrayList<>();
        customers.add(customer);

        Customer jo = customerController.findByCustomerEmail("jo@gmail.com");
        customers.add(jo);

        Map<String, String> answers  = new HashMap<>();
        answers.put("Q1.","87654321");
        answers.put("Q2.","Not Answered");
        answers.put("Q3.","2");
        answers.put("Q4.","gabi@gmail.com");
        answers.put("Q5.","4");
        answers.put("Q6.","yes");
        answers.put("Q7.","yes");
        answers.put("Q8.","no");
        answers.put("Q9.","1,2");
        Answer answer = new Answer( new AnswerId("123"),customer, answers);
        answerList.add(answer);
        File file = new File("base.core/src/main/java/eapli/base/surveys/questionnaire.txt");
        FileInputStream f1 = null;
        try {
            f1 = new FileInputStream(file);
            byte[] surveyFile = new byte[(int) file.length()];
            f1.read(surveyFile);
            f1.close();
            createSurvey("3213", "questionnaire1- >16 female", 10, surveyFile, 16, "FEMALE", customers, answerList);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Answer> answerJoList = new ArrayList<>();
        Survey survey = qaController.findSurveyId("3213");
        Map<String, String> answersJo  = new HashMap<>();
        answersJo.put("Q1.","12345678");
        answersJo.put("Q2.","Not Answered");
        answersJo.put("Q3.","3");
        answersJo.put("Q4.","jo@gmail.com");
        answersJo.put("Q5.","1");
        answersJo.put("Q6.","yes");
        answersJo.put("Q7.","no");
        answersJo.put("Q8.","yes");
        answersJo.put("Q9.","2,3");
        Answer answerJ = new Answer( new AnswerId("456"),jo, answersJo);
        answerJoList.add(answerJ);
        answerController.saveSurveyAnswered(survey,answerJoList);

        Set<CustomerPostalAddress> postalAddresss = new HashSet<>();
        CustomerPostalAddress customerPostalAddresss = new CustomerPostalAddress("Corne", 2, "Miami", "USA", 90214);
        postalAddresss.add(customerPostalAddresss);
        Customer customerr = new Customer(CustomerId.valueOf("8765"), CustomerFirstName.valueOf("Fernando"), CustomerLastName.valueOf("Hall"), CustomerEmailAdress.valueOf("ferni@gmail.com"), CustomerPhoneNumber.valueOf("910477205"), CustomerVATIdentifier.valueOf("9263749"), CustomerBirthDay.valueOf(new Date(Calendar.YEAR + 98, 5, 2)), CustomerGender.valueOf("MALE"), postalAddresss);
        List<Customer> customerss = new ArrayList<>();
        List<Answer> answerListt = new ArrayList<>();
        customerss.add(customerr);

        Map<String, String> answersS = new HashMap<>();
        answersS.put("Q1.","12345678");
        answersS.put("Q2.","Not Answered");
        answersS.put("Q3.","1");
        answersS.put("Q4.","ferni@gmail.com");
        answersS.put("Q5.","1");
        answersS.put("Q6.","yes");
        answersS.put("Q7.","no");
        answersS.put("Q8.","yes");
        answersS.put("Q9.","1,2,3");
        Answer answerR = new Answer(new AnswerId("12365"), customerr, answersS);
        answerListt.add(answerR);
        File filee = new File("base.core/src/main/java/eapli/base/surveys/questionnaire3.txt");
        try {
            f1 = new FileInputStream(filee);
            byte[] surveyFilee = new byte[(int) filee.length()];
            f1.read(surveyFilee);
            f1.close();
            createSurvey2("1289", "questionnaire1- male", 6, surveyFilee, "MALE", customerss, answerListt);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Set<CustomerPostalAddress> postalAddressss = new HashSet<>();
        CustomerPostalAddress customerPostalAddressss = new CustomerPostalAddress("oi", 4, "Miami", "USA", 90214);
        postalAddressss.add(customerPostalAddressss);
        Customer customerrr = new Customer(CustomerId.valueOf("8335"), CustomerFirstName.valueOf("António"), CustomerLastName.valueOf("Dal"), CustomerEmailAdress.valueOf("to@gmail.com"), CustomerPhoneNumber.valueOf("910487205"), CustomerVATIdentifier.valueOf("9543749"), CustomerBirthDay.valueOf(new Date(Calendar.YEAR + 70, 5, 23)), CustomerGender.valueOf("MALE"), postalAddressss);
        List<Customer> customersss = new ArrayList<>();
        List<Answer> answerListtt = new ArrayList<>();
        customersss.add(customerrr);
        customersss.add(customerController.findByCustomerEmail("mary@gmail.com"));

        Map<String, String> answerssS = new HashMap<>();
        answerssS.put("Q1.","12345678");
        answerssS.put("Q2.","Not Answered");
        answerssS.put("Q3.","3");
        answerssS.put("Q4.","to@gmail.com");
        answerssS.put("Q5.","1");
        answerssS.put("Q6.","yes");
        answerssS.put("Q7.","no");
        answerssS.put("Q8.","yes");
        Answer answerrR = new Answer(new AnswerId("1005"), customerrr, answerssS);
        answerListtt.add(answerrR);
        File fileee = new File("base.core/src/main/java/eapli/base/surveys/questionnaire2.txt");
        try {
            f1 = new FileInputStream(fileee);
            byte[] surveyFileee = new byte[(int) fileee.length()];
            f1.read(surveyFileee);
            f1.close();
            createSurvey3("665", "questionnaire2- >20", 6, surveyFileee, 20, customersss, answerListtt);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private void createSurvey(String id, String description, int period, byte[] surveyfile, int surveyruleA, String surveyruleG, List<Customer> customers, List<Answer> answers) {

        try {
            controller.createSurveyAnswered(new AlphanumericCode(id), new SurveyDescription(description), new SurveyPeriod(period), surveyfile, new SurveyRule(surveyruleA, surveyruleG), customers, answers);
        } catch (final ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", id);
            LOGGER.trace("Assuming existing record", e);
        }
    }

    private void createSurvey2(String id, String description, int period, byte[] surveyfile, String surveyruleG, List<Customer> customers, List<Answer> answers) {

        try {
            controller.createSurveyAnswered(new AlphanumericCode(id), new SurveyDescription(description), new SurveyPeriod(period), surveyfile, new SurveyRule(surveyruleG), customers, answers);
        } catch (final ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", id);
            LOGGER.trace("Assuming existing record", e);
        }
    }

    private void createSurvey3(String id, String description, int period, byte[] surveyfile, int surveyruleA, List<Customer> customers, List<Answer> answers) {

        try {
            controller.createSurveyAnswered(new AlphanumericCode(id), new SurveyDescription(description), new SurveyPeriod(period), surveyfile, new SurveyRule(surveyruleA), customers, answers);
        } catch (final ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", id);
            LOGGER.trace("Assuming existing record", e);
        }
    }

    private void saveSurveyAnswered(Survey s, List<Answer> answers){
            answerController.saveSurveyAnswered(s, answers);
    }
}
