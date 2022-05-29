package eapli.base.ordersmanagement.customer.repositories;

import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.customer.domain.CustomerId;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends DomainRepository<CustomerId, Customer> {
    Optional<Customer> findByUsername(Username username);

    Customer findByCustomerId(CustomerId customerId);
    Customer findByCustomerEmail(String emailAddress);
    Customer findByCustomerIdOrder(String customerId);
    Optional<Customer> findByEmail(EmailAddress email);

    public List<Customer> findAllCustomers();


}
