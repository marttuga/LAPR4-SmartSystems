package eapli.base.surveys.src.application;

import eapli.framework.io.util.Console;

public class SurveysService {

    public int showOptionsQuestionaires() {
        int option = -1;
        System.out.println("===================================================");
        System.out.println("               Questionnaires:          ");
        System.out.println("=================================================\n");
        System.out.println("questionnaire 1");
        System.out.println("questionnaire 2");
        System.out.println("questionnaire 3");
        System.out.println("===================================================");
        System.out.println("0. Back\n\n");
        option = Console.readInteger("Please select an option");
        return option;
    }


    public String questionnaires(int options) {
        switch (options) {
            case (1):
                return "C:\\Users\\marti\\Documents\\2ANO2SEMESTRE\\LAPR4\\LEI21_22_S4_2DK_01\\base.core\\src\\main\\java\\eapli\\base\\surveys\\questionnaire.txt";
            case (2):
                return "C:\\Users\\marti\\Documents\\2ANO2SEMESTRE\\LAPR4\\LEI21_22_S4_2DK_01\\base.core\\src\\main\\java\\eapli\\base\\surveys\\questionnaire2.txt";
            case (3):
                return "C:\\Users\\marti\\Documents\\2ANO2SEMESTRE\\LAPR4\\LEI21_22_S4_2DK_01\\base.core\\src\\main\\java\\eapli\\base\\surveys\\questionnaire3.txt";
        }
        return null;
    }
}
