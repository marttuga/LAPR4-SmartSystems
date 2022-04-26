package eapli.base.ordersmanagement.customer.repositories;

import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.customer.domain.CustomerId;
import eapli.base.ordersmanagement.customer.domain.EmailAddress;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.Optional;

public interface CustomerRepository extends DomainRepository<CustomerId, Customer> {
    Optional<Customer> findByUsername(Username username);

    Customer findByCustomerId(CustomerId customerId);

    Optional<Customer> findByEmail(EmailAddress email);
}
