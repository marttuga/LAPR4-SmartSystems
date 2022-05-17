package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.customer.applicaion.RegisterCustomerController;
import eapli.base.ordersmanagement.customer.domain.*;
import eapli.base.ordersmanagement.customer.repositories.CustomerRepository;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CustomerBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            CustomerBootstrapper.class);
    private final RegisterCustomerController registerCustomerController = new RegisterCustomerController();
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();
    private final Date date = new Date(1994,04,23);

    @Override
    public boolean execute() {
        createCostumer("Jonathan", "Mendes", "jonathan@gmail.com",
                date, "937263547", "0936527",
                "Sunset Boulevard", 78, "Los angeles", "USA",90210);
        return true;
    }

    private void createCostumer(String customerFirstName,
                                String customerLastName,
                                String customerEmailAddress,
                                Date customerBirthDay,
                                String customerPhoneNumber,
                                String customerVatIdentifier,
                                String street, int doorNumber, String city, String country, int postalCode) {

        try {
            Set<CustomerPostalAddress> postalAddress = new HashSet<CustomerPostalAddress>();
            CustomerPostalAddress customerPostalAddress = new CustomerPostalAddress(street, doorNumber, city, country, postalCode);
            postalAddress.add(customerPostalAddress);

            registerCustomerController.registerCustomer(customerFirstName, customerLastName,
                    customerEmailAddress,  customerPhoneNumber,
                    customerVatIdentifier,  customerBirthDay,
                    "MALE", postalAddress);
        } catch (final ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", customerEmailAddress);
            LOGGER.trace("Assuming existing record", e);
        }
    }
}
