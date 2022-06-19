package eapli.base.ordersmanagement.survey.application;


import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.survey.domain.Survey;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SurveyNotificationOutput {

    protected SurveyNotificationOutput() {
    }

    public static boolean outPutNotification(List<Customer> customers) throws IOException {


        try {
      /*      FileWriter myWriter;
            String outputLocation;
            for (Customer fa : s.getCustomers()) {
                File myFile = new File("surveys//"+"notification" + fa.getCustomerFirstName().toString()+ "_Survey.txt");
                myWriter = new FileWriter(myFile, true);
*/
            FileWriter myWriter;
            String outputLocation = null;
            File file = new File("base.core//src//main//java//eapli//base//surveys//SurveyNotification.txt");
            outputLocation = file.getAbsolutePath();
            myWriter = new FileWriter(outputLocation, true);

            try {

                    myWriter.write("Clients able to answer: \n"+ customers.toString());
                    //myWriter.write(" \nHello " + c.getCustomerFirstName() + ", ");
                    //myWriter.write(" \nYou are now able to answer this new questionnaire available in your menu: ");
                    //myWriter.write(" \nID: " + s.getAlphanumericCode() + "Description: " + s.getSurveyDescription());


                   // myWriter.write("");




            } finally {
                myWriter.close();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error outputting the form request to a txt!");
            return false;
        }

        return true;
    }

}
