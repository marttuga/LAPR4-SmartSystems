package eapli.base.ordersmanagement.survey.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.survey.domain.Survey;
import eapli.base.ordersmanagement.survey.dto.SurveyDTO;
import eapli.base.ordersmanagement.survey.repositories.SurveyRepository;
import eapli.framework.io.util.Console;

import java.util.List;
import java.util.stream.Collectors;

public class SurveysService {

    private final SurveyRepository surveyRepository = PersistenceContext.repositories().survey();

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

    public List<SurveyDTO> findAll(){
        List<Survey> surveyList =  surveyRepository.findAllAnswered();
        return surveyList.stream().map(this::fromEntityToDTO).collect(Collectors.toList());
    }
    public List<Survey> findAllSurveys(){
        return surveyRepository.findAll();

    }

    public Survey findByID(String s){
        return surveyRepository.findByID(s);
    }

    public SurveyDTO fromEntityToDTO(Survey survey){
        return new SurveyDTO(survey.getAlphanumericCode(), survey.getSurveyDescription());
    }

}
