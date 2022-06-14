package eapli.base.ordersmanagement.survey.application;

import eapli.base.ordersmanagement.survey.domain.Survey;
import eapli.base.ordersmanagement.survey.dto.SurveyDTO;
import eapli.base.surveys.src.domain.FileAnswers;

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
        FileAnswers.outPutResume(answer);
    }

    public SurveyDTO fromEntityToDTO(Survey survey) {
    return surveysService.fromEntityToDTO(survey);
    }
}
