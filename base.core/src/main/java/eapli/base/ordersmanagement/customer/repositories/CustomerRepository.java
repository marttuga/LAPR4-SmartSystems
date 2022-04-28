package eapli.base.ordersmanagement.customer.repositories;

import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.customer.domain.MecanographicNumber;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends DomainRepository<MecanographicNumber, Customer> {
    Optional<Customer> findByUsername(Username username);

    Customer findByCustomerId(MecanographicNumber mecanographicNumber);

    Optional<Customer> findByEmail(EmailAddress email);

    public List<Customer> findAllCustomers();


}
