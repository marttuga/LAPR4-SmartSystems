
package eapli.base.ordersmanagement.customer.application.services;

import eapli.base.utilitarianClasses.EmailSystem;
import eapli.base.utilitarianClasses.PasswordGeneratorAlgorithm;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.customer.domain.*;
import eapli.base.ordersmanagement.customer.repositories.CustomerRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.time.util.Calendars;

import java.util.*;

@ApplicationService
public class CustomerServices {
    private final UserManagementService userService = AuthzRegistry.userService();
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();

    Customer customer1;
    Random rand =new Random();

    public Customer createCustomerShortInfo( CustomerFirstName customerFirstName, CustomerLastName customerLastName, EmailAddress customerEmailAddress, CustomerPhoneNumber customerPhoneNumber, CustomerVATIdentifier customerVatIdentifier ) {
        int id = rand.nextInt(999999999);
        CustomerId customerId = new CustomerId(id);
        /*do{
            id = rand.nextInt(999999999);
            customerId.setCustomerId(id);
        }while (customer1.hasIdentity(customerId));*/
        Customer customer= new Customer(customerId,customerFirstName,customerLastName,customerEmailAddress,customerPhoneNumber,customerVatIdentifier);
        String password = PasswordGeneratorAlgorithm.generatesPassword();
        createSystemUserForCustomer(customer.getCustomerId().toString(), password, customer.getCustomerFirstName().toString(), customer.getCustomerEmailAddress().toString());
        EmailSystem.sendEmail(customer.getCustomerEmailAddress().toString(), customer.getCustomerId().toString(), password);
        this.customerRepository.save(customer);
        return customer;
    }

    public Customer createCustomer(CustomerFirstName customerFirstName, CustomerLastName customerLastName, EmailAddress customerEmailAddress, CustomerPhoneNumber customerPhoneNumber, CustomerVATIdentifier customerVatIdentifier, CustomerBirthDay customerBirthDay, CustomerGender customerGender, Set<CustomerPostalAddress> customerPostalAddresses) {
        int id = rand.nextInt(999999999);
        CustomerId customerId = new CustomerId(id);
        /*do{
            id = rand.nextInt(999999999);
            customerId.setCustomerId(id);
        }while (customer1.hasIdentity(customerId));*/
        Customer customer= new Customer(customerId,customerFirstName,customerLastName,customerEmailAddress,customerPhoneNumber,customerVatIdentifier,customerBirthDay,customerGender, customerPostalAddresses);
        String password = PasswordGeneratorAlgorithm.generatesPassword();
        createSystemUserForCustomer(customer.getCustomerId().toString(), password, customer.getCustomerFirstName().toString(), customer.getCustomerEmailAddress().toString());
        EmailSystem.sendEmail(customer.getCustomerEmailAddress().toString(), customer.getCustomerId().toString(), password);
        this.customerRepository.save(customer);
        return customer;
    }

    public SystemUser createSystemUserForCustomer(final String username, final String password, final String name, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.CLIENT_USER);
        return userService.registerNewUser(username, password, name, name, email, roles, Calendars.now());
    }


}

