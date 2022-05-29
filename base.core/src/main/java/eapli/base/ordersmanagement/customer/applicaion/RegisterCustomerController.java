
package eapli.base.ordersmanagement.customer.applicaion;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.customer.domain.*;
import eapli.base.ordersmanagement.customer.repositories.CustomerRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.utilitarianClasses.EmailSystem;
import eapli.base.utilitarianClasses.PasswordGeneratorAlgorithm;
import eapli.framework.application.UseCaseController;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;


@UseCaseController
public class RegisterCustomerController {

    private final UserManagementService userService = AuthzRegistry.userService();
    //private final CustomerServices customerService = new CustomerServices();
    private final TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();
    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();

    public Customer registerCustomerShortInfo( final String customerFirstName, final String customerLastName, final String customerEmailAddress, final String  customerPhoneNumber, final String customerVatIdentifier ) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.SALES_CLERK_USER);
        String uniqueID = RandomStringUtils.randomAlphanumeric(8);
        final var newCustomer = new CustomerBuilder().withCustomerId(uniqueID).withFisrtName(CustomerFirstName.valueOf(customerFirstName)).withLastName(CustomerLastName.valueOf(customerLastName)).withEmailAdress(CustomerEmailAdress.valueOf(customerEmailAddress)).withPhoneNumber(CustomerPhoneNumber.valueOf(customerPhoneNumber)).withVATIdentifier(CustomerVATIdentifier.valueOf(customerVatIdentifier)).buildShortInfo();
        String password = PasswordGeneratorAlgorithm.generatesPassword();
        EmailSystem.sendEmail(newCustomer.getCustomerEmailAddress().toString(), newCustomer.getCustomerId().toString(), password);
        return customerRepository.save(newCustomer);
    }

    public Customer registerCustomer(final String customerFirstName, final String customerLastName, final String customerEmailAddress, final String  customerPhoneNumber, final String customerVatIdentifier, final Date customerBirthDay, final String customerGender, final Set<CustomerPostalAddress> customerPostalAddresses) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.SALES_CLERK_USER);
        String uniqueID = RandomStringUtils.randomAlphanumeric(8);
        final var newCustomer = new CustomerBuilder().withCustomerId(uniqueID).withFisrtName(CustomerFirstName.valueOf(customerFirstName)).withLastName(CustomerLastName.valueOf(customerLastName)).withEmailAdress(CustomerEmailAdress.valueOf(customerEmailAddress)).withPhoneNumber(CustomerPhoneNumber.valueOf(customerPhoneNumber)).withVATIdentifier(CustomerVATIdentifier.valueOf(customerVatIdentifier)).withBirthday(CustomerBirthDay.valueOf(customerBirthDay)).withGender(CustomerGender.valueOf(customerGender)).withPostalAdresses(customerPostalAddresses).build();
        String password = PasswordGeneratorAlgorithm.generatesPassword();
        EmailSystem.sendEmail(newCustomer.getCustomerEmailAddress().toString(), newCustomer.getCustomerId().toString(), password);
        return customerRepository.save(newCustomer);
    }

    public List<Customer> findAllCustomers(){
        return customerRepository.findAllCustomers();
    }

    public Customer findByCustomerId(CustomerId c){
        return customerRepository.findByCustomerId(c);
    }
    public Customer findByCustomerEmail(String c){
        return customerRepository.findByCustomerEmail(c);
    }
    public Customer findByCustomerIdOrder(String c){
        return customerRepository.findByCustomerIdOrder(c);
    }

}