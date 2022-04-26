
package eapli.base.ordersmanagement.customer.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.customer.application.services.CustomerServices;
import eapli.base.ordersmanagement.customer.domain.*;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Calendar;

@UseCaseController
public class RegisterCustomerController {
    private final CustomerServices customerService = new CustomerServices();
    private final TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();
    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    public Customer registerCustomer(String customerId, String emailAddress, String name, String postalAddress, String phoneNumber, Calendar birthDate, String gender, String vatIdentifier, String password) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.SALES_CLERK_USER);

        txCtx.beginTransaction();

        final SystemUser newUser = customerService.createSystemUserForCustomer(customerId, password, name, emailAddress);
        final Customer customer = customerService.createCustomer(newUser, customerId, emailAddress, phoneNumber, name, birthDate, postalAddress, vatIdentifier, gender);

        txCtx.commit();

        return customer;
    }
}
