/*
package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.warehousemanagement.application.UploadFileController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.Scanner;
*/
/*

public class UploadFileUI extends AbstractUI {

    UploadFileController uploadFileController = new UploadFileController();

    Scanner in = new Scanner(System.in);

    @Override
    protected boolean doShow() {
        int option = -1;
            try {
                System.out.println("==================================================");
                System.out.println("=======Welcome to Upload File Menu!=======");
                System.out.println("==================================================");
                option = Console.readInteger("1- Upload JSON file\n");
                System.out.println("==================================================");
                //option = Console.readInteger("0- Back.\n");
                if(option == 1){
                    System.out.println("Successful operation!");
                    uploadFileController.uploadFile("WarehousePlan.json");

                }else if(option == 0){
                    System.out.println("Exiting ...");
                } else System.out.println("Option does not exist!");

            } catch (Exception e) {
                System.out.println("Something went wrong");
            }
        return false;
    }

        public String headline () {
            return "Upload JSON file.";
        }

}
*/
