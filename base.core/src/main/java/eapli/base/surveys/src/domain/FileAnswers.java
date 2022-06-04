package eapli.base.surveys.src.domain;

import java.io.FileWriter;

public class FileAnswers {

    protected FileAnswers() {
    }

    public static boolean outPutResume(String answer) {
        try {
            FileWriter myWriter;
            String outputLocation = null;
            outputLocation = "C:\\Users\\marti\\Documents\\2ANO2SEMESTRE\\LAPR4\\LEI21_22_S4_2DK_01\\base.core\\src\\main\\java\\eapli\\base\\surveys\\Answers.txt";

            myWriter = new FileWriter(outputLocation, true);

            try {
                myWriter.write(answer+"\n");

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
