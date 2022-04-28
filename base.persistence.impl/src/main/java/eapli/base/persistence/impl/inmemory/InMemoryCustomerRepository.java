package eapli.base.persistence.impl.inmemory;

import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.customer.domain.MecanographicNumber;

import eapli.base.ordersmanagement.customer.repositories.CustomerRepository;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.List;
import java.util.Optional;

public class InMemoryCustomerRepository extends InMemoryDomainRepository<Customer, MecanographicNumber>
        implements CustomerRepository {

    static {
        InMemoryInitializer.init();
    }


    @Override
    public Optional<Customer> findByUsername(Username username) {
        return Optional.empty();
    }

    @Override
    public Customer findByCustomerId(MecanographicNumber mecanographicNumber) {
        return data().get(mecanographicNumber);
    }

    @Override
    public Optional<Customer> findByEmail(EmailAddress email) {
        return Optional.ofNullable(data().get(email));
    }



    @Override
    public List<Customer> findAllCustomers() {
       return findAllCustomers();
    }
}
