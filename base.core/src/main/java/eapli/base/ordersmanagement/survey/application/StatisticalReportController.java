package eapli.base.ordersmanagement.survey.application;

import eapli.base.ordersmanagement.answer.application.AnswerService;

import eapli.base.ordersmanagement.answer.domain.Answer;
import eapli.base.ordersmanagement.survey.dto.SurveyDTO;

import java.io.IOException;
import java.util.List;

public class StatisticalReportController {



    public final SurveysService surveysService = new SurveysService();

    public final AnswerService answerService = new AnswerService();



    public String getStatisticalReport(String id) throws IOException {
        return surveysService.getStatisticalReport(id);
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
