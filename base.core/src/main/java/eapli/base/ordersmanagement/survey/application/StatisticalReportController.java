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

    public int calculateUniverse(String id){
        return answerService.universe(id);
    }

    public int calculateAnswers(String id){
        return answerService.answers(id);
    }

    //public double calculatePercentage(String id){
       // return answerService.calculatePercentage(id);
  //  }
}
