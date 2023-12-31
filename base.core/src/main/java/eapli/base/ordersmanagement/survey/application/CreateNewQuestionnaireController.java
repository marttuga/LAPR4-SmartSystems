package eapli.base.ordersmanagement.survey.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.answer.domain.Answer;
import eapli.base.ordersmanagement.answer.repository.AnswerRepository;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.customer.repositories.CustomerRepository;
import eapli.base.ordersmanagement.survey.domain.*;
import eapli.base.ordersmanagement.survey.repositories.SurveyRepository;
import eapli.base.surveys.src.domain.FileAnswersOutput;
import eapli.base.surveys.src.domain.FormGrammarLexer;
import eapli.base.surveys.src.domain.FormGrammarParser;
import eapli.base.surveys.src.domain.ThrowingErrorListener;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.io.IOException;
import java.util.List;

public class CreateNewQuestionnaireController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final SurveyRepository repo = PersistenceContext.repositories().survey();
    private final CustomerRepository r = PersistenceContext.repositories().customers();

    private final AnswerRepository answerRepository = PersistenceContext.repositories().answer();
    private final TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();

    public Survey createSurvey(AlphanumericCode alphanumericCode, SurveyDescription description, SurveyPeriod surveyPeriod, byte[] surveyFile, SurveyRule surveyRule, List<Customer> customers, List<Answer> answers) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER);
        txCtx.beginTransaction();
        saveCustomers(customers);
        final Survey survey= new Survey(alphanumericCode,description,surveyPeriod,surveyFile, surveyRule,customers, answers);
        this.repo.save(survey);

        txCtx.commit();

        return survey;
    }

    public Survey createSurveyAnswered(AlphanumericCode alphanumericCode, SurveyDescription description, SurveyPeriod surveyPeriod, byte[] surveyFile, SurveyRule surveyRule, List<Customer> customers, List<Answer> answers){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER);
        txCtx.beginTransaction();
        saveCustomers(customers);
        saveAnswers(answers);
        final Survey survey= new Survey(alphanumericCode,description,surveyPeriod,surveyFile, surveyRule,customers, answers);
        this.repo.save(survey);

        txCtx.commit();

        return survey;
    }



    public void saveCustomers( List<Customer> customers) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER);
        for (Customer c: customers) {
            this.r.save(c);
        }

    }

    public void saveAnswers( List<Answer> answers) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER);
        for (Answer a: answers) {
            this.answerRepository.save(a);
        }

    }

    public static boolean validateSurvey(String fileName) throws IOException {
        ParserRuleContext tree = null;
        boolean valid = false;
        try {
            CharStream codePointCharStream = CharStreams.fromFileName(fileName);
            FormGrammarLexer lexer = new FormGrammarLexer(codePointCharStream);
            lexer.removeErrorListeners();
            lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

            CommonTokenStream tokens = new CommonTokenStream(lexer);

            FormGrammarParser parser = new FormGrammarParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(ThrowingErrorListener.INSTANCE);

            //ParseTree tree = parser.survey();
            tree = parser.start();

        } catch (ParseCancellationException e) {
            System.out.println("File does not pass the grammar:\n" + e.getMessage());
        }
        if (tree != null) valid = true;

        return valid;
    }

    public int showOptionsRules() {
        int option = -1;
        System.out.println("===================================================");
        System.out.println("               Survey Rules:          ");
        System.out.println("=================================================\n");
        System.out.println("1-Minimum Age Rule");
        System.out.println("2-Gender Rule");
        System.out.println("3-Age and gender Rule");
        System.out.println("===================================================");
        System.out.println("0. Back\n\n");
        option = Console.readInteger("Please select an option");
        return option;
    }

    public void outPutNotification(List<Customer> c) throws IOException {
        SurveyNotificationOutput.outPutNotification(c);
    }
}
