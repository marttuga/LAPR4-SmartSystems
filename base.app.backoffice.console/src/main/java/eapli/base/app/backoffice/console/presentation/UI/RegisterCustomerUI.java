package eapli.base.app.backoffice.console.presentation.UI;


import eapli.base.ordersmanagement.customer.applicaion.RegisterCustomerController;

import eapli.base.utilitarianClasses.Utils;
import eapli.framework.presentation.console.AbstractUI;

import java.util.*;

import eapli.base.ordersmanagement.customer.domain.*;



public class RegisterCustomerUI extends AbstractUI {

  /*  private static final String F = null;
    private static final String M = null;
    private static final String O = null;*/
    private final RegisterCustomerController controller = new RegisterCustomerController();


    @Override
    protected boolean doShow() {
        try {
            System.out.println("==================================================");
            System.out.println("=======Welcome to costumer's registration!========");
            System.out.println("==================================================");
            String firstName = Utils.readLine("Please insert your first name:");
            System.out.println("==================================================");
            String lastName = Utils.readLine("Please insert your last name:");
            System.out.println("==================================================");
            String emailAddress = Utils.readLine("Please insert your email Address:");
            System.out.println("==================================================");
            String phoneNumber = Utils.readLine("Please insert your phone number:");
            System.out.println("==================================================");
            String vatIdentifier = Utils.readLine("Please insert a VAT identification number:");
            System.out.println("==================================================");
            String option = Utils.readLine("Do you wish to add more information:");
            switch (option.toUpperCase()){
                case "Y":
                    System.out.println("==================================================");
                    Date birthday = Utils.readDateFromConsole("Please insert your birthday:(Use this format: dd/MM/yyyy)");
                    System.out.println("==================================================");
                    String gender = Utils.readLine("Please insert your gender:(F/M/O)");
                    switch (gender.toUpperCase()) {
                        case "F":
                            gender = String.valueOf(CustomerGender.FEMALE);
                            break;
                        case "M":
                            gender = String.valueOf(CustomerGender.MALE);
                            break;
                        case "O":
                            gender = String.valueOf(CustomerGender.OTHER);
                            break;
                    }
                    final Set<CustomerPostalAddress> address = new HashSet<>();
                    System.out.println("==================================================");
                    String street = Utils.readLine("Insert the Customer's Address (Street)");
                    System.out.println("==================================================");
                    int doorNumber = Utils.readIntegerFromConsole("Insert the Customer's Address (Door number)");
                    System.out.println("==================================================");
                    String city = Utils.readLine("Insert the Customer's Address (City)");
                    System.out.println("==================================================");
                    String country = Utils.readLine("Insert the Customer's Address (Country)");
                    System.out.println("==================================================");
                    int postalCode = Utils.readIntegerFromConsole("Insert the Customer's Address (Postal Code)");
                    CustomerPostalAddress customerPostalAddress = CustomerPostalAddress.valueOf(street, doorNumber, city, country, postalCode);
                    address.add(customerPostalAddress);
                    controller.registerCustomer(firstName, lastName, emailAddress, phoneNumber, vatIdentifier, birthday, gender, address);
                    break;
                case "N":
                    controller.registerCustomerShortInfo(firstName, lastName, emailAddress, phoneNumber, vatIdentifier);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Something went wrong");
            return false;
        }
    }

    @Override
    public String headline() {
        return "Register New Customer";
    }
}

