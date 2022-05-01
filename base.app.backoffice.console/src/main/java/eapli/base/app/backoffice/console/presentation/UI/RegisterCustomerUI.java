package eapli.base.app.backoffice.console.presentation.UI;


import eapli.base.ordersmanagement.customer.applicaion.RegisterCustomerController;

import eapli.base.utilitarianClasses.Utils;
import eapli.framework.presentation.console.AbstractUI;

import java.util.HashSet;
import java.util.Set;
import eapli.base.ordersmanagement.customer.domain.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class RegisterCustomerUI extends AbstractUI {

    private static final String F = null;
    private static final String M = null;
    private static final String O = null;
    private final RegisterCustomerController controller = new RegisterCustomerController();

    Scanner in = new Scanner(System.in);

    @Override
    protected boolean doShow() {
        try {
            System.out.println("==================================================");
            System.out.println("=======Welcome to costumer's registration!========");
            System.out.println("==================================================");
            System.out.println("Please insert your first name:");
            String firstName = in.nextLine();
            System.out.println("==================================================");
            System.out.println("Please insert your last name:");
            String lastName = in.nextLine();
            System.out.println("==================================================");
            System.out.println("Please insert your email Address:");
            String emailAddress = in.nextLine();
            System.out.println("==================================================");
            System.out.println("Please insert your phone number:");
            String phoneNumber = in.nextLine();
            System.out.println("==================================================");
            System.out.println("Please insert a VAT identification number:");
            String vatIdentifier = in.nextLine();

            System.out.println("==================================================");
            System.out.println("Do you wish to add more information:");
            String option = in.nextLine();
            if (option.equalsIgnoreCase("y")){
                System.out.println("Please insert your birthday:(Use this format: dd/MM/yyyy)");
                String birthday = in.nextLine();
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(birthday);;
                System.out.println("==================================================");
                System.out.println("Please insert your gender:(F/M/O)");
                String gender = in.nextLine();
                String customerGender = null;
                if (gender.equalsIgnoreCase(F)){
                    customerGender = String.valueOf(CustomerGender.FEMALE);
                }else{
                    if (gender.equalsIgnoreCase(M)){
                        customerGender = String.valueOf(CustomerGender.MALE);
                    }else {
                        if (gender.equalsIgnoreCase(O)) {
                            customerGender = String.valueOf(CustomerGender.OTHER);
                        }
                    }
                }
                final Set<CustomerPostalAddress> address = new HashSet<>();
                do {
                    System.out.println("==================================================");
                    System.out.println("Insert the Customer's Address (Street)");
                    String street = in.nextLine();
                    System.out.println("==================================================");
                    System.out.println("Insert the Customer's Address (Door number)");
                    int doorNumber = in.nextInt();
                    System.out.println("==================================================");
                    System.out.println("Insert the Customer's Address (City)");
                    String city = in.nextLine();
                    System.out.println("==================================================");
                    System.out.println("Insert the Customer's Address (Country)");
                    String country = in.nextLine();
                    System.out.println("==================================================");
                    System.out.println("Insert the Customer's Address (Postal Code)");
                    int postalCode = in.nextInt();
                    CustomerPostalAddress customerPostalAddress = CustomerPostalAddress.valueOf(street,doorNumber,city,country,postalCode);
                    address.add(customerPostalAddress);
                } while (Utils.confirm("Write another address? (Y or N)"));
                controller.registerCustomer(firstName,lastName,emailAddress,phoneNumber,vatIdentifier,date,gender,address);
            } else {if (option.equalsIgnoreCase("n")){
                controller.registerCustomerShortInfo(firstName,lastName,emailAddress,phoneNumber,vatIdentifier);
            }
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

