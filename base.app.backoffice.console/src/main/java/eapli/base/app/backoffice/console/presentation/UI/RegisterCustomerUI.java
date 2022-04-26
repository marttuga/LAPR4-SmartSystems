/*
package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.ordersmanagement.customer.application.RegisterCustomerController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class RegisterCustomerUI extends AbstractUI {

    private final RegisterCustomerController controller = new RegisterCustomerController();

    @Override
    protected boolean doShow() {

        try {
            final String customerId = Console.readLine("Customer Id:");
            final String name = Console.readLine("Name:");
            final String emailAddress = Console.readLine("Email Address:");
            final String vatId = Console.readLine("VAT identifier:");
            final Set<String> address = new HashSet<>();
            boolean show;
            do {
                address.add(Console.readLine("Address:"));
            } while (Utils.confirm("Write another address? (Y or N)"));
            final String phoneNumber = Console.readLine("Phone Number:");
            final String gender = Console.readLine("Gender:");

            final String birthDate = Console.readLine("Birth date (year/month/day):");

            System.out.printf("%s created with success.\n", controller.registerCustomer(customerId, emailAddress, name, address, phoneNumber, birthDate, gender,vatId));

        } catch (final IntegrityViolationException ex) {
            System.out.println("There is already a customer with that Mecanographic Number.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Add a new Customer to the system";
    }



    @Override
    protected boolean doShow() {
        return false;
    }

    @Override
    public String headline() {
        return null;
    }
}
*/
