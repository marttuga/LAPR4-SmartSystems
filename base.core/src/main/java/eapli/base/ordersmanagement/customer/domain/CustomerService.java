/*
package eapli.base.ordersmanagement.customer.domain;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.customer.repositories.CustomerRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.time.util.Calendars;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@ApplicationService
public class CustomerService {
    private final UserManagementService userService = AuthzRegistry.userService();
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customerUsers();

    public Customer createCustomer(EmailAddress emailAddress, PhoneNumber phoneNumber, String name, BirthDate birthDate, PostalAddress postalAddress, Gender gender, VATIdentifier vatIdentifier) {

        final CustomerBuilder customerBuilder = new CustomerBuilder();
        Customer customer;
        customer = customerBuilder.build();
        customerBuilder.withEmailAddress(emailAddress).withPhoneNumber(phoneNumber).withName(name).withBirthDate(birthDate).withPostalAddress(postalAddress).withGender(gender).withVATIdentifier(vatIdentifier);
        this.customerRepository.save(customer);

        return customer;
    }

    public SystemUser createSystemUserForCustomer(final String username, final String password, final String firstName, final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.CLIENT_USER);
        return userService.registerNewUser(username, password, firstName, lastName, email, roles, Calendars.now());
    }
}
*/
