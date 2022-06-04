package eapli.base.surveys.src.application;

import eapli.framework.io.util.Console;

public class SurveysService {

    public int showOptionsQuestionaires() {
        int option = -1;
        System.out.println("===================================================");
        System.out.println("               Questionnaires:          ");
        System.out.println("=================================================\n");
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("===================================================");
        System.out.println("0. Back\n\n");
        option = Console.readInteger("Please select an option");
        return option;
    }


    public String questionnaires(int options) {
        switch (options) {
            case (1):
                return "questionnaire.txt";
            case (2):
                return "questionnaire2.txt";
            case (3):
                return "questionnaire3.txt";
        }
        return null;
    }
}
