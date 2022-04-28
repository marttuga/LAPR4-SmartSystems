package eapli.base.Utils;

import java.io.FileWriter;

public class EmailSystem {

    /**
     * Hide the implicit constructor.
     */
    private EmailSystem(){}

    public static boolean sendEmail(String emailAddress, String role,String username, String password)  {

        try {
            FileWriter myWriter;
            myWriter = new FileWriter("Email_With_Credentials.txt", true);

            try {
                myWriter.write(String.format("Email Address: %s %n", emailAddress));
                myWriter.write(String.format("Role: %s %n",role));
                myWriter.write(String.format("Username: %s %n", username));
                myWriter.write(String.format("PassWord: %s %n", password));

            } finally {
                myWriter.write("-------------------------------------\n");
                myWriter.close();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error sending the email!");
            return false;
        }

        return true;
    }
}
