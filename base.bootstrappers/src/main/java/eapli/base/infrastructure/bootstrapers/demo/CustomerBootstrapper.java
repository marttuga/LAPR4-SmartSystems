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

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CustomerBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            CustomerBootstrapper.class);
    private final RegisterCustomerController registerCustomerController = new RegisterCustomerController();
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();

    @Override
    public boolean execute() {
        createCostumer("Mike", "Chang", "mike@gmail.com",
                    new Date(23/04/2001), "917147649", "1234567", "male",
                    "Oxford Street", 12, "London", "England",5000);
        return true;
    }

    private void createCostumer(String customerFirstName,
            String customerLastName,
            String customerEmailAddress,
            Date customerBirthDay,
            String customerPhoneNumber,
            String customerVatIdentifier,
            String customerGender,
            String street, int doorNumber, String city, String country, int postalCode) {

        Customer customer;
        try {
            Set<CustomerPostalAddress> postalAddress = new HashSet<CustomerPostalAddress>();
            CustomerPostalAddress customerPostalAddress = new CustomerPostalAddress(street, doorNumber, city, country, postalCode);
            postalAddress.add(customerPostalAddress);

            customer = registerCustomerController.registerCustomer(customerFirstName, customerLastName,
                   customerEmailAddress,  customerPhoneNumber,
                    customerVatIdentifier,  customerBirthDay,
                    customerGender, postalAddress);
            customerRepository.save(customer);
        } catch (final ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", customerEmailAddress);
            LOGGER.trace("Assuming existing record", e);
        }
    }
}
