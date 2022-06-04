package eapli.base.surveys.src.application;

public class QuestionnaireAnswerController {
    private final SurveysService surveysService = new SurveysService();

    public int showOptionsQuestionaires() {
        return surveysService.showOptionsQuestionaires();
    }

    public String questionnaires(int options) {
        return surveysService.questionnaires(options);
    }
}
