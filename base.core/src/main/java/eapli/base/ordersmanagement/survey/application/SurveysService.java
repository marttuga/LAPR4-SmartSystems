package eapli.base.ordersmanagement.survey.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.survey.domain.AlphanumericCode;
import eapli.base.ordersmanagement.survey.domain.Survey;
import eapli.base.ordersmanagement.survey.dto.SurveyDTO;
import eapli.base.ordersmanagement.survey.repositories.SurveyRepository;
import eapli.framework.io.util.Console;

import java.io.IOException;
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

    //TODO method to only show already answered surveys
    public List<SurveyDTO> findAll(){
        List<Survey> surveyList =  surveyRepository.findAll();
        surveyList.removeIf(s -> {
            assert s.getAnswers() != null;
            return s.getAnswers().isEmpty();
        });
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
    public void printSurveysList(List<SurveyDTO> ist) {
        for (SurveyDTO c : ist) {
            System.out.println("Questionnaire Code- " + c.getSurveyID()+ " / Description- "+ c.getSurveyDescription()+ "\n");
        }
    }

    public String getStatisticalReport(String id) throws IOException {
        var survey = surveyRepository.ofIdentity(AlphanumericCode.valueOf(id)).orElseThrow(IllegalArgumentException::new);
        return survey.getStatisticalReport();
    }

}
