package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.surveys.src.application.CreateNewQuestionnaireController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.io.IOException;


public class CreateNewQuestionnaireUI extends AbstractUI {

    private final CreateNewQuestionnaireController ctrl = new CreateNewQuestionnaireController();

    @Override
    protected boolean doShow() {
        String path = Console.readNonEmptyLine("Insert path: ", "The file path is empty.");

        try{
            System.out.println("Path inserted! \n");
            ctrl.validation(path);
            System.out.println("Successfull Operation!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    @Override
    public String headline() {
        return "Create a new Questionnaire";
    }
}
