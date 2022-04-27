package eapli.base.persistence.impl.jpa;

import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.customer.domain.CustomerId;
import eapli.base.ordersmanagement.customer.domain.EmailAddress;
import eapli.base.ordersmanagement.customer.repositories.CustomerRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;
import java.util.*;

public class JpaCustomerRepository extends JpaAutoTxRepository<Customer, CustomerId, CustomerId> implements CustomerRepository {

    public JpaCustomerRepository(TransactionalContext autoTx) {
        super(autoTx, "customerId");
    }


    @Override
    public Customer findByCustomerId(CustomerId customerId) {
        TypedQuery<Customer> query = super.createQuery("SELECT c FROM Customer c WHERE c.customerId = :customerId", Customer.class);
        query.setParameter("customerId", customerId);
        return query.getSingleResult();
    }


    @Override
    public Optional<Customer> findByEmail(EmailAddress emailAddress) {
        final Map<String, Object> param = new HashMap<>();
        param.put("emailAddress", emailAddress);
        return matchOne("e.systemUser.email =:emailAddress", param);
    }

    @Override
    public Optional<Customer> findByUsername(Username name) {
        final Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return matchOne("e.systemUser.username =:name", params);
    }

    @Override
    public List<Customer> findAllCustomers() {
        List<Customer> customers = (List<Customer>) findAll();
        return customers;
    }


}
