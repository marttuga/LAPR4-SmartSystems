package eapli.base.ordersmanagement.survey.application;

import eapli.base.ordersmanagement.answer.application.AnswerService;

import eapli.base.ordersmanagement.answer.domain.Answer;
import eapli.base.ordersmanagement.survey.dto.SurveyDTO;
import eapli.base.surveys.src.domain.EvalVisitorReport;
import eapli.base.surveys.src.domain.FormGrammarLexer;
import eapli.base.surveys.src.domain.FormGrammarParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class StatisticalReportController {
    public final SurveysService surveysService = new SurveysService();

    public final AnswerService answerService = new AnswerService();



    public String getStatisticalReport(String id) throws IOException {
        FileInputStream fis = new FileInputStream(new File("/Users/ruidias/lei21_22_s4_2dk_01/base.core/src/main/java/eapli/base/surveys/questionnaire.txt"));
        FormGrammarLexer lexer = new FormGrammarLexer(new ANTLRInputStream(fis));
        org.antlr.v4.runtime.CommonTokenStream tokens = new CommonTokenStream(lexer);
        FormGrammarParser parser = new FormGrammarParser(tokens);
        ParseTree tree = parser.start(); // parse
        EvalVisitorReport eval = new EvalVisitorReport(getAllAnswers(id));
        return eval.visit(tree);
    }

    public List<Answer> getAllAnswers(String id) { return  answerService.getAllAnswersToList(id); }

    public List<SurveyDTO> showSurveys(){
        return surveysService.findAll();
    }

    public int calculateUniverse(String id){
        return answerService.universe(id);
    }

    public int calculateAnswers(String id){
        return answerService.answers(id);
    }

    public double calculatePercentage(String id){
        return answerService.calculatePercentage(id);
    }
}
