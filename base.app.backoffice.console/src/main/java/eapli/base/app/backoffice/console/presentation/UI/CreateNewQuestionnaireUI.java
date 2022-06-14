package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.customer.applicaion.RegisterCustomerController;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.customer.domain.CustomerGender;
import eapli.base.ordersmanagement.customer.repositories.CustomerRepository;
import eapli.base.ordersmanagement.order.domain.SalesRegion;
import eapli.base.ordersmanagement.survey.application.CreateNewQuestionnaireController;
import eapli.base.ordersmanagement.survey.domain.*;
import eapli.base.utilitarianClasses.Utils;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CreateNewQuestionnaireUI extends AbstractUI {
    private final CustomerRepository r = PersistenceContext.repositories().customers();

    private final CreateNewQuestionnaireController ctrl = new CreateNewQuestionnaireController();
    private final RegisterCustomerController controller = new RegisterCustomerController();

    @Override
    protected boolean doShow() {
        final String fileName = Console.readLine("Please insert the questionnaire file path:");
/*        boolean valid = false;
        try {
            valid = ctrl.validateSurvey(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //if (valid) {
        String alphanumericCode = Console.readLine("Survey Alphanumeric code: ");
        String surveyDescription = Console.readLine("Survey Description: ");
        int surveyPeriod = Console.readInteger("Survey period (days): ");
        List<Customer> allCustomers;
        List<Customer> customers;
        int numBytes;
        SurveyRule s;
        int ruleA;
        String ruleG;


        int answer= ctrl.showOptionsRules();
        switch (answer) {
            case (1):
                 ruleA = Console.readInteger("Minimum age rule: ");
                 s = new SurveyRule((ruleA));

               allCustomers = controller.findAllCustomers();
                customers = new ArrayList<>();

                try {

                    for (Customer c : allCustomers) {
                        if (c.getCustomerBirthDay().getAge() > s.getRuleAge()) {
                            customers.add(c);

                        }
                    }

                    System.out.println(customers);
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
                            ctrl.saveSurvey(ctrl.createSurvey(AlphanumericCode.valueOf(alphanumericCode), SurveyDescription.valueOf(surveyDescription), SurveyPeriod.valueOf(surveyPeriod), surveyFile, s, customers));

                        } catch (Exception e) {
                            System.out.println("Error saving the questionnaire: " + e);
                        }
                    }
                } catch (IOException ex) {
                    throw new IllegalStateException("Error saving the questionnaire: " + ex);
                }
                break;




            case (2):
                 ruleG = Console.readLine("Gender rule:(F/M/O)");
                switch (ruleG.toUpperCase()) {
                    case "F":
                        ruleG = String.valueOf(CustomerGender.FEMALE);
                        break;
                    case "M":
                        ruleG = String.valueOf(CustomerGender.MALE);
                        break;
                    case "O":
                        ruleG = String.valueOf(CustomerGender.OTHER);
                        break;
                }
                 s = new SurveyRule((ruleG));

                 allCustomers = controller.findAllCustomers();
                 customers = new ArrayList<>();

                try {

                    for (Customer c : allCustomers) {
                        if (c.getCustomerGender().toString().equals(s.getRuleGender())) {
                            customers.add(c);

                        }
                    }

                    System.out.println(customers);
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
                            ctrl.saveSurvey(ctrl.createSurvey(AlphanumericCode.valueOf(alphanumericCode), SurveyDescription.valueOf(surveyDescription), SurveyPeriod.valueOf(surveyPeriod), surveyFile, s, customers));

                        } catch (Exception e) {
                            System.out.println("Error saving the questionnaire: " + e);
                        }
                    }
                } catch (IOException ex) {
                    throw new IllegalStateException("Error saving the questionnaire: " + ex);
                }
                break;




            case (3):
                 ruleA = Console.readInteger("Minimum age rule: ");

                 ruleG = Console.readLine("Gender rule:(F/M/O)");
                switch (ruleG.toUpperCase()) {
                    case "F":
                        ruleG = String.valueOf(CustomerGender.FEMALE);
                        break;
                    case "M":
                        ruleG = String.valueOf(CustomerGender.MALE);
                        break;
                    case "O":
                        ruleG = String.valueOf(CustomerGender.OTHER);
                        break;
                }
                 s = new SurveyRule(ruleA,(ruleG));

                 allCustomers = controller.findAllCustomers();
                 customers = new ArrayList<>();

                try {

                    for (Customer c : allCustomers) {
                        if (c.getCustomerBirthDay().getAge() > s.getRuleAge() && c.getCustomerGender().toString().equals(s.getRuleGender())) {
                            customers.add(c);

                        }
                    }

                    System.out.println(customers);
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
                            ctrl.saveSurvey(ctrl.createSurvey(AlphanumericCode.valueOf(alphanumericCode), SurveyDescription.valueOf(surveyDescription), SurveyPeriod.valueOf(surveyPeriod), surveyFile, s, customers));

                        } catch (Exception e) {
                            System.out.println("Error saving the questionnaire: " + e);
                        }
                    }
                } catch (IOException ex) {
                    throw new IllegalStateException("Error saving the questionnaire: " + ex);
                }
                break;
        }



        //}
        return false;
    }

    //}
    @Override
    public String headline() {
        return "Create a new Questionnaire";
    }
}
