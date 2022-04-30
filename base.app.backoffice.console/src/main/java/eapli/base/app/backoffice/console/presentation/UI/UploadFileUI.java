package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.warehousemanagement.application.UploadFileController;
import eapli.framework.presentation.console.AbstractUI;

import java.util.Scanner;

public class UploadFileUI extends AbstractUI {

    UploadFileController uploadFileController = new UploadFileController();

    Scanner in = new Scanner(System.in);

    @Override
    protected boolean doShow() {
        int option = -1;
        do {
            System.out.println("==================================================");
            System.out.println("=======Welcome to Upload File Menu!=======");
            System.out.println("==================================================");
            System.out.println("1- Upload JSON file");
            System.out.println("==================================================");
            System.out.println("0- Back.");
            switch (option) {
                case 0:
                    System.out.println("Exiting ...");
                    break;
                case 1:
                    uploadFileController.uploadFile("WarehousePlan.json");
                default:
                    System.out.println("Option does not exist!");
                    break;
            }

        } while (option != 0) ;
            try {
                System.out.println("Successful operation!");
            } catch (Exception e) {
                System.out.println("Something went wrong");
            }
        return false;
    }

        public String headline () {
            return "Upload JSON file.";
        }

}
