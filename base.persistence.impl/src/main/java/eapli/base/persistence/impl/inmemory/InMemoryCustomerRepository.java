package eapli.base.persistence.impl.inmemory;

import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.customer.domain.CustomerId;
import eapli.base.ordersmanagement.customer.domain.EmailAddress;
import eapli.base.ordersmanagement.customer.repositories.CustomerRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.List;
import java.util.Optional;

public class InMemoryCustomerRepository extends InMemoryDomainRepository<Customer, CustomerId>
        implements CustomerRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Optional<Customer> findByUsername(Username name) {
        return matchOne(e -> e.user().username().equals(name));
    }

    @Override
    public Customer findByCustomerId(CustomerId customerId) {
        return data().get(customerId);
    }

    @Override
    public Optional<Customer> findByEmail(EmailAddress emailAddress) {
        return matchOne(e -> e.user().email().equals(emailAddress));
    }

    @Override
    public List<Customer> findAllCustomers() {
        return null;
    }
}
