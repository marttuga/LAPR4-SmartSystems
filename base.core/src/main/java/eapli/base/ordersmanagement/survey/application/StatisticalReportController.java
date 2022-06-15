package eapli.base.ordersmanagement.survey.application;

import eapli.base.ordersmanagement.answer.application.AnswerService;
import eapli.base.ordersmanagement.survey.dto.SurveyDTO;

import java.util.List;

public class StatisticalReportController {
    public final SurveysService surveysService = new SurveysService();

    public final AnswerService answerService = new AnswerService();

    public List<SurveyDTO> showSurveys(){
        return surveysService.findAll();
    }

    public String calculateUniverse(String id){
        return answerService.universe(id);
    }
}
