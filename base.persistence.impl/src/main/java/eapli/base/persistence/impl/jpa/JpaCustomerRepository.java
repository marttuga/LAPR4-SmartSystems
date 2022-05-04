package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.customer.domain.CustomerId;

import eapli.base.ordersmanagement.customer.repositories.CustomerRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;

public class JpaCustomerRepository extends JpaAutoTxRepository<Customer, CustomerId, CustomerId> implements CustomerRepository {

    public JpaCustomerRepository(TransactionalContext autoTx) {
        super(autoTx, "customerId");
    }

    public JpaCustomerRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "customerID");
    }
    @Override
    public Optional<Customer> findByUsername(Username username) {
        return Optional.empty();
    }

    @Override
    public Customer findByCustomerId(CustomerId customerId) {
        TypedQuery<Customer> query = super.createQuery("SELECT c FROM Customer c WHERE c.customerId.customerId = :customerId", Customer.class);
        query.setParameter("customerId", customerId);
        return query.getSingleResult();
    }

    @Override
    public Customer findByCustomerIdOrder(String customerId) {
        Query q = entityManager().createQuery("SELECT cust FROM Customer cust " +
                " WHERE cust.customerId.customerId = :customerId");
        q.setParameter("customerId", customerId);
        return  (Customer) q.getSingleResult();
    }


    @Override
    public Optional<Customer> findByEmail(EmailAddress email) {
        final Map<String, Object> param = new HashMap<>();
        param.put("emailAddress", email);
        return matchOne("e.systemUser.email =:emailAddress", param);
    }




    @Override
    public List<Customer> findAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        TypedQuery<Customer> query = super.createQuery("SELECT DISTINCT c FROM Customer c", Customer.class);
        for (Customer c: query.getResultList()) {
            customerList.add(c);
        }
        return customerList;
    }

  /*  @Override
    public Optional<Customer> findByUsername(Username name) {
        final Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return matchOne("e.systemUser.username =:name", params);
    }*/




}
