package eapli.base.ordersmanagement.survey.application;

import eapli.base.ordersmanagement.answer.domain.Answer;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.survey.domain.Survey;
import eapli.base.ordersmanagement.survey.dto.SurveyDTO;
import eapli.base.surveys.src.domain.FileAnswersOutput;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class QuestionnaireAnswerController {
    private final SurveysService surveysService = new SurveysService();

    public int showOptionsQuestionaires() {
        return surveysService.showOptionsQuestionaires();
    }

    public String questionnaires(int options) {
        return surveysService.questionnaires(options);
    }

    public List<Survey> findAllSurveys() {
        return surveysService.findAllSurveys();
    }
    public Survey findSurveyId(String id) {
        return surveysService.findByID(id);
    }

    public void outPutResume(String answer) {
        FileAnswersOutput.outPutResume(answer);
    }

    public SurveyDTO fromEntityToDTO(Survey survey) {
    return surveysService.fromEntityToDTO(survey);
    }

    public void printSurveysList(List<SurveyDTO> orderList) {
        surveysService.printSurveysList(orderList);
    }

    public String checkAnswer( File id) throws IOException {
        return surveysService.checkAnswer(id);
    }
    public String surveysPath(Survey id) throws IOException {
        return surveysService.surveysPath(id);
    }

}
