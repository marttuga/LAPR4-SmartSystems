package eapli.base.app.backoffice.console.presentation.UI;

import eapli.framework.io.util.Console;

import java.util.Scanner;

public class UploadFileUI {

    public static void showMenu() {
        Scanner in = new Scanner(System.in);
        int option = 0;
        do {
            option = showOptions();
            switch (option) {
                case 0:
                    System.out.println("Exiting ...");
                    break;
                case 1:
                    ;
                    break;
                default:
                    System.out.println("Option does not exist!");
                    break;
            }

        }while (option != 0) ;
    }

        private static int showOptions () {
            int option = -1;
            System.out.println("===================================================");
            System.out.println("               Upload File Menu:          ");
            System.out.println("=================================================\n");
            System.out.println("1- ");
            System.out.println("===================================================");
            System.out.println("0. Back\n\n");
            option = Console.readInteger("Please select an option");
            return option;
        }

}
