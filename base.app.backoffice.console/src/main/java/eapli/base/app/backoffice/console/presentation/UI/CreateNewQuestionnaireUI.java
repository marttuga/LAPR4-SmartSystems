package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.ordersmanagement.survey.application.CreateNewQuestionnaireController;
import eapli.base.ordersmanagement.survey.domain.AlphanumericCode;
import eapli.base.ordersmanagement.survey.domain.Survey;
import eapli.base.ordersmanagement.survey.domain.SurveyDescription;
import eapli.base.ordersmanagement.survey.domain.SurveyPeriod;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class CreateNewQuestionnaireUI extends AbstractUI {

    private final CreateNewQuestionnaireController ctrl = new CreateNewQuestionnaireController();

    @Override
    protected boolean doShow() {
        final String fileName = Console.readLine("Please insert the questionnaire file path:");
        boolean passes = false;
/*        try {
            passes = ctrl.validateSurvey(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //
            String alphanumericCode = Console.readLine("Survey Alphanumeric code: ");
            String surveyDescription = Console.readLine("Survey Description: ");
            int surveyPeriod = Console.readInteger("Survey period (days): ");

            int numBytes;
            try {
                File file = new File(fileName);
                String extension = FilenameUtils.getExtension(file.getName()).toLowerCase();
                if (!extension.equals("txt"))
                    throw new IllegalStateException("The file's extension should be .txt!");
                byte[] surveyFile = new byte[(int) file.length()];
                FileInputStream fileInputStream = new FileInputStream(file);
                numBytes = fileInputStream.read(surveyFile);
                if (numBytes == 0) System.out.println("Please try again.");
                else {
                    fileInputStream.close();
                    try {
                        ctrl.saveSurvey(ctrl.createSurvey(AlphanumericCode.valueOf(alphanumericCode), SurveyDescription.valueOf(surveyDescription), SurveyPeriod.valueOf(surveyPeriod), surveyFile));

                    } catch (Exception e) {
                        System.out.println("Error saving the questionnaire: " + e);
                    }
                }
            } catch (IOException ex) {
                throw new IllegalStateException("Error saving the questionnaire: " + ex);
            }
        return false;
        }

    //}
    @Override
    public String headline() {
        return "Create a new Questionnaire";
    }
}
