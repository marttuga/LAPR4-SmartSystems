package eapli.base.ordersmanagement.survey.application;

import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.survey.domain.Survey;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SurveyNotificationOutput {

    protected SurveyNotificationOutput() {
    }

    public static boolean outPutNotification(Survey s) throws IOException {
       /* try {
            String filePath = "surveys\\";
            String fileName = "SurveyNotification";
            String fileType = "txt";

            for (Customer c : s.getCustomers()) {
                File myFile = new File(filePath+fileName + c.getCustomerFirstName() + "." + fileType);
                // String outputLocation = null;
                //File file = new File("base.core\\src\\main\\java\\eapli\\base\\surveys\\SurveyNotification.txt");
                // outputLocation = myFile.getAbsolutePath();
                //myWriter = new FileWriter(outputLocation, true);

                try (FileWriter myWriter = new FileWriter(myFile)) {
                    myWriter.write("To: " + c.getCustomerEmailAddress());
                    myWriter.write(" \nHello " + c.getCustomerFirstName() + ", ");
                    myWriter.write(" \nYou are now able to answer this new questionnaire available in your menu: ");
                    myWriter.write(" \nID: " + s.getAlphanumericCode() + "Description: " + s.getSurveyDescription());
                    //myWriter.write(not+"\n");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error outputting the form request to a txt!");
        }*/
        /*try {
            for (Customer fa : s.getCustomers()) {
                FileWriter myWriter;
                String outputLocation = "base.core//src//main//java//eapli//base//surveys//notifications" +fa.getCustomerFirstName() + "SurveyNotification.txt";
                myWriter = new FileWriter(outputLocation);

                try {
                myWriter.write("To: " + fa.getCustomerEmailAddress());
                myWriter.write(" \nHello " + fa.getCustomerFirstName() + ", ");
                myWriter.write(" \nYou are now able to answer this new questionnaire available in your menu: ");
                myWriter.write(" \nID: " + s.getAlphanumericCode() + "Description: " + s.getSurveyDescription());

                myWriter.write("");
                } finally {
                    myWriter.close();
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error outputting the form request to a txt!");
            return false;
        }
        return true;*/
        try {
            FileWriter myWriter;
            String outputLocation;
            for (Customer fa : s.getCustomers()) {
                File myFile = new File("surveys//"+"notification" + fa.getCustomerFirstName().toString()+ "_Survey.txt");
                myWriter = new FileWriter(myFile, true);

                try {

                    myWriter.write("To: " + fa.getCustomerEmailAddress());
                    myWriter.write(" \nHello " + fa.getCustomerFirstName() + ", ");
                    myWriter.write(" \nYou are now able to answer this new questionnaire available in your menu: ");
                    myWriter.write(" \nID: " + s.getAlphanumericCode() + "Description: " + s.getSurveyDescription());


                    myWriter.write("");
                } finally {
                    myWriter.close();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error outputting the form request to a txt!");
            return false;
        }

        return true;
    }

}
