package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.order.application.CheckOpenOrderController;
import eapli.base.ordersmanagement.survey.application.QuestionnaireAnswerController;

import eapli.base.ordersmanagement.survey.domain.Survey;
import eapli.base.ordersmanagement.survey.dto.SurveyDTO;
import eapli.base.utilitarianClasses.Utils;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;


import java.io.*;
import java.util.*;


public class QuestionnaireAnswerUI extends AbstractUI {
    private static final QuestionnaireAnswerController questionnaireAnswerController = new QuestionnaireAnswerController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CheckOpenOrderController checkOpenOrderController = new CheckOpenOrderController();

    @Override
    protected boolean doShow() {
        Customer customer;
        String email = authz.session().get().authenticatedUser().email().toString();
        customer = checkOpenOrderController.findByCustomerEmail(email);
        List<Survey> allSurvey = questionnaireAnswerController.findAllSurveys();
        List<SurveyDTO> surveysForCustomer = new ArrayList<>();

        for (Survey s : allSurvey) {
            for (Customer c : s.getCustomers()) {
                if (c == customer) {
                    surveysForCustomer.add(questionnaireAnswerController.fromEntityToDTO(s));
                }
            }
        }

        for (SurveyDTO sd:surveysForCustomer) {
            System.out.println(sd);
        }
        String oi = Utils.readLine("Choose the questionnaire to answer: ");
        Survey o= questionnaireAnswerController.findSurveyId(oi);

        //String q = questionnaireAnswerController.questionnaires(questionnaireAnswerController.showOptionsQuestionaires());

        List<String> questionary = new LinkedList();
        Scanner in = new Scanner(System.in);
        List<String> answers = new LinkedList();
        try {
            System.out.println("========================================");

            Scanner sc = new Scanner(new FileReader((Arrays.toString(o.getSurveyFile()))));
            boolean flag = false; //se for uma questao
            boolean flagSC = false; //se for de escolha
            boolean flagTf = false; //se for de texto
            questionary.add("========================================"); //para mostrar o questionario junto com as respostas
            questionary.add(" ");
            answers.add("========================================"); //para mostrar so as respostas
            answers.add(" ");

            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                if (linha.contains("CHERRY22")) {
                    answers.add(linha);
                }
                System.out.println(linha);

                questionary.add(linha);

                if (linha.equals("Q1.")) {
                    flag = true;
                }
                if (flag) {
                    if (linha.equals("Obligatoriness: Optional.")) { //verificar se a questao é opcional
                        String ifSingChoiceOrText = ""; //guarda a linha q diz se a resposta é escolha ou texto
                        int count = 1;
                        while (!linha.equals("")) {
                            linha = sc.nextLine();
                            System.out.println(linha);
                            questionary.add(linha);
                            if (count == 2) { // tem de andar duas linhas para ver o tipo da resposta
                                ifSingChoiceOrText = linha;
                            }
                            count++;
                        }
                        System.out.println("===================="); //pergunta se quer responder
                        System.out.println("Do you wish to answer?");
                        System.out.println("1.Yes");
                        System.out.println("2.No");
                        String answer = in.nextLine();
                        System.out.println("====================");
                        System.out.println();
                        if (answer.equals("2")) {

                            answers.add("Not Answered");
                            questionary.add(questionary.size() - 1, "Not Answered"); //se nao quiser fica como n respondida no txt e continua a ler

                            linha = sc.nextLine();
                            System.out.println(linha);
                            questionary.add(linha);
                        }
                        if (answer.equals("1") && ifSingChoiceOrText.equals("Type: Single-Choice.")) { //se quiser responder e for de escolha multipla
                            flagSC = true;
                        } else if (answer.equals("1")) { //se n for de escolha multipla
                            flagTf = true;
                        }
                    }

                    if (linha.equals("Type: Single-Choice.") || flagSC) { //se for de escolha multipla

                        while (!linha.equals("")) {
                            linha = sc.nextLine();
                            System.out.println(linha);
                            questionary.add(linha);
                        }
                        //  System.out.println(linha);

                        String answer = in.nextLine(); //quando a linha estiver vazia vai pedir a resposta
                        String finalAnswer = "";
                        int index = questionary.size() - 1;
                        while (finalAnswer.equals("")) {
                            try {
                                String[] aux = questionary.get(index).split(". ");
                                if (aux[0].equals(answer)) {
                                    finalAnswer = aux[1];
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            index--;
                        }

                        questionary.add(questionary.size() - 1, "Answer:" + finalAnswer + "\n");//adicionar a resposta à lista q vai para o txt
                        answers.add("Answer:" + finalAnswer);
                        flagSC = false;

                    } else if (linha.equals("") || flagTf) { //se a linha for vazia e for de free text vai ler a proxima linha q é a resposta e adiciona la ao txt
                        String answer = in.nextLine();
                        if (!answer.equals("")) {

                            questionary.add(questionary.size() - 1, "Answer:" + answer + "\n");
                            answers.add("Answer:" + answer);
                        }
                        flagTf = false;
                    }
                }
            }
            sc.close();

            for (String a : questionary) {
                questionnaireAnswerController.outPutResume(a); // print para o txt
            }
            System.out.println("========================================");
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            System.out.println("Ocorreu um erro Tipo RunTime");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("System Error");
        }


        return false;
    }

    @Override
    public String headline() {
        return "Questionnaire";
    }
}
