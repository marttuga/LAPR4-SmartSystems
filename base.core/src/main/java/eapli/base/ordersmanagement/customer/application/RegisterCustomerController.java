/*
package eapli.base.ordersmanagement.customer.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.customer.domain.*;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

@UseCaseController
public class RegisterCustomerController {
    private final CustomerService customerService = new CustomerService();
    private final TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();
    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    public Customer registerCustomer(EmailAddress emailAddress, Name name, PostalAddress postalAddress, PhoneNumber phoneNumber, BirthDate birthDate, Gender gender, VATIdentifier vatIdentifier) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.SALES_CLERK_USER);

        txCtx.beginTransaction();

        //final SystemUser newUser = customerService.createSystemUserForCustomer(mecanographicNumber, password, firstName, lastName, email);
        final Customer customer = customerService.createCustomer(emailAddress, phoneNumber, name, birthDate, postalAddress, vatIdentifier, gender);

        txCtx.commit();

        return customer;
    }
}
*/
