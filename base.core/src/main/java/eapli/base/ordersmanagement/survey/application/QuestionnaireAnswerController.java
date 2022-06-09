package eapli.base.ordersmanagement.survey.application;

import eapli.base.surveys.src.domain.FileAnswers;

public class QuestionnaireAnswerController {
    private final SurveysService surveysService = new SurveysService();

    public int showOptionsQuestionaires() {
        return surveysService.showOptionsQuestionaires();
    }

    public String questionnaires(int options) {
        return surveysService.questionnaires(options);
    }

    public void outPutResume(String answer) {
        FileAnswers.outPutResume(answer);
    }


}
