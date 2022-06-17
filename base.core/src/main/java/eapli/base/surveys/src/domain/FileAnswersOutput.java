package eapli.base.surveys.src.domain;

import java.io.File;
import java.io.FileWriter;

public class FileAnswersOutput {

    protected FileAnswersOutput() {
    }

    public static boolean outPutResume(String answer) {
        try {
            FileWriter myWriter;
            String outputLocation = null;
            File file = new File("base.core\\src\\main\\java\\eapli\\base\\surveys\\Answers.txt");
            outputLocation = file.getAbsolutePath();
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
