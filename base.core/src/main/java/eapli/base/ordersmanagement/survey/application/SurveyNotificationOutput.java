package eapli.base.ordersmanagement.survey.application;

import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.survey.domain.AlphanumericCode;
import eapli.base.ordersmanagement.survey.domain.Survey;
import eapli.base.ordersmanagement.survey.domain.SurveyDescription;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SurveyNotificationOutput {

    protected SurveyNotificationOutput() {
    }

    public static boolean outPutNotification(Survey s, Customer c) throws IOException {
        try {
            int totalCount = s.getCustomers().size();

            //String filePath = "C:\\Users\\marti\\Documents\\2ANO2SEMESTRE\\LAPR4\\LEI21_22_S4_2DK_01\\base.core\\src\\main\\java\\eapli\\base\\surveys";
            String fileName= "SurveyNotification";
            String fileType = "txt";

            for(int counter = 0; counter < totalCount; counter++) {

                //tbd
                File myFile = new File( fileName + c.getCustomerFirstName() + "." + fileType);
    /*
       will result into files qrCODE0.png, qrCODE1.png, etc..
        created at the given location
    */

                FileWriter myWriter= new FileWriter(myFile);
               // String outputLocation = null;
                //File file = new File("base.core\\src\\main\\java\\eapli\\base\\surveys\\SurveyNotification.txt");
               // outputLocation = myFile.getAbsolutePath();
                //myWriter = new FileWriter(outputLocation, true);

                try {
                    myWriter.write("To: " + c.getCustomerEmailAddress());
                    myWriter.write(" \nHello " + c.getCustomerFirstName() + ", ");
                    myWriter.write(" \nYou are now able to answer this new questionnaire available in your menu: ");
                    myWriter.write(" \nID: " + s.getAlphanumericCode() + "Description: " + s.getSurveyDescription());
                    myWriter.close();

                    //myWriter.write(not+"\n");

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
