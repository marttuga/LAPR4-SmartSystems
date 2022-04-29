package eapli.base.app.backoffice.console.presentation.UI;


import eapli.base.ordersmanagement.customer.application.RegisterCustomerController;

import eapli.base.utilitarianClasses.Utils;
import eapli.framework.presentation.console.AbstractUI;

import java.util.HashSet;
import java.util.Set;
import eapli.base.ordersmanagement.customer.domain.*;
import eapli.framework.general.domain.model.EmailAddress;
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
            CustomerFirstName customerFirstName = CustomerFirstName.valueOf(firstName);
            System.out.println("Please insert your last name:");
            String lastName = in.nextLine();
            CustomerLastName customerLastName = CustomerLastName.valueOf(lastName);
            System.out.println("==================================================");
            System.out.println("Please insert your email Address:");
            String emailAddress = in.nextLine();
            EmailAddress customerEmailAddress = EmailAddress.valueOf(emailAddress);
            System.out.println("==================================================");
            System.out.println("Please insert your phone number:");
            String phoneNumber = in.nextLine();
            CustomerPhoneNumber customerPhoneNumber = CustomerPhoneNumber.valueOf(phoneNumber);
            System.out.println("==================================================");
            System.out.println("Please insert a VAT identification number:");
            String vatIdentifier = in.nextLine();
            CustomerVATIdentifier customerVATIdentifier = CustomerVATIdentifier.valueOf(vatIdentifier);
            System.out.println("==================================================");
            if (Utils.confirm("Do you wish to add more information?(Y or N)")){
                System.out.println("Please insert your birthday:(Use this format: dd/MM/yyyy)");
                String birthday = in.nextLine();
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(birthday);
                CustomerBirthDay customerBirthDay= CustomerBirthDay.valueOf(date);
                System.out.println("==================================================");
                System.out.println("Please insert your gender:(F/M/O)");
                String gender = in.nextLine();
                CustomerGender customerGender = null;
                if (gender.equalsIgnoreCase(F)){
                    customerGender = CustomerGender.FEMALE;
                }else{
                    if (gender.equalsIgnoreCase(M)){
                        customerGender = CustomerGender.MALE;
                    }else {
                        if (gender.equalsIgnoreCase(O)) {
                            customerGender = CustomerGender.MALE;
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
                controller.registerCustomer(customerFirstName,customerLastName,customerEmailAddress,customerPhoneNumber,customerVATIdentifier,customerBirthDay,customerGender,address);
            }else {
                controller.registerCustomerShortInfo(customerFirstName,customerLastName,customerEmailAddress,customerPhoneNumber,customerVATIdentifier);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Something went wrong");
            return false;
        }
    }

    @Override
    public String headline() {
        return null;
    }
}

