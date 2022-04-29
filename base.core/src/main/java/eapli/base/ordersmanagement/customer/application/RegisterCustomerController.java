
package eapli.base.ordersmanagement.customer.application;

import eapli.base.clientusermanagement.domain.MecanographicNumber;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.customer.application.services.CustomerServices;
import eapli.base.ordersmanagement.customer.domain.*;
import eapli.base.ordersmanagement.customer.repositories.CustomerRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.List;
import java.util.Set;


@UseCaseController
public class RegisterCustomerController {
    private final CustomerServices customerService = new CustomerServices();
    private final TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();
    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();

    public Customer registerCustomerShortInfo(CustomerId customerId, CustomerFirstName customerFirstName, CustomerLastName customerLastName, EmailAddress customerEmailAddress, CustomerPhoneNumber customerPhoneNumber, CustomerVATIdentifier customerVatIdentifier ) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.SALES_CLERK_USER);

        txCtx.beginTransaction();

        final Customer customer = customerService.createCustomerShortInfo(customerId, customerFirstName, customerLastName, customerEmailAddress, customerPhoneNumber, customerVatIdentifier);

        txCtx.commit();


        return customer;
    }

    public Customer registerCustomer(CustomerId customerId, CustomerFirstName customerFirstName, CustomerLastName customerLastName, EmailAddress customerEmailAddress, CustomerPhoneNumber customerPhoneNumber, CustomerVATIdentifier customerVatIdentifier, CustomerBirthDay customerBirthDay, CustomerGender customerGender, Set<CustomerPostalAddress> customerPostalAddresses ) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.SALES_CLERK_USER);

        txCtx.beginTransaction();

        final Customer customer = customerService.createCustomer(customerId, customerFirstName, customerLastName, customerEmailAddress, customerPhoneNumber, customerVatIdentifier, customerBirthDay,customerGender,customerPostalAddresses);

        txCtx.commit();


        return customer;
    }

    public List<Customer> findAllCustomers(){
        return customerRepository.findAllCustomers();
    }

    public Customer findByCustomerId(CustomerId c){
        return customerRepository.findByCustomerId(c);
    }
}

