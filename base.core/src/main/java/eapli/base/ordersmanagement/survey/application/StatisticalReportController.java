package eapli.base.ordersmanagement.survey.application;

import eapli.base.ordersmanagement.survey.domain.Survey;

import java.util.List;

public class StatisticalReportController {
    public final SurveysService surveysService = new SurveysService();

    public List<Survey> showSurveys(){
        return surveysService.findAll();
    }
}
