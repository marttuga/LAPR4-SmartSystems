package eapli.base.ordersmanagement.survey.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.survey.domain.AlphanumericCode;
import eapli.base.ordersmanagement.survey.domain.Survey;
import eapli.base.ordersmanagement.survey.domain.SurveyDescription;
import eapli.base.ordersmanagement.survey.domain.SurveyPeriod;
import eapli.base.ordersmanagement.survey.repositories.SurveyRepository;
import eapli.base.surveys.src.domain.FormGrammarLexer;
import eapli.base.surveys.src.domain.FormGrammarParser;
import eapli.base.surveys.src.domain.ThrowingErrorListener;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.io.IOException;
import java.nio.file.Paths;

public class CreateNewQuestionnaireController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final SurveyRepository repo = PersistenceContext.repositories().survey();
    private final TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();

    public Survey createSurvey(AlphanumericCode alphanumericCode, SurveyDescription description, SurveyPeriod surveyPeriod, byte[] surveyFile) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER);
        txCtx.beginTransaction();
        final Survey survey= new Survey(alphanumericCode,description,surveyPeriod,surveyFile);
        this.repo.save(survey);
        txCtx.commit();

        return survey;
    }

    public void saveSurvey(Survey survey) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER);
        repo.save(survey);
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
            tree = parser.lprog();

        } catch (ParseCancellationException e) {
            System.out.println("File does not pass the grammar:\n" + e.getMessage());
        }
        if (tree != null) valid = true;

        return valid;
    }
}