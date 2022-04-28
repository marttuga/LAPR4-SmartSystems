package eapli.base.persistence.impl.jpa;

import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.customer.domain.MecanographicNumber;

import eapli.base.ordersmanagement.customer.repositories.CustomerRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;
import java.util.*;

public class JpaCustomerRepository extends JpaAutoTxRepository<Customer, MecanographicNumber, MecanographicNumber> implements CustomerRepository {

    public JpaCustomerRepository(TransactionalContext autoTx) {
        super(autoTx, "customerId");
    }


    @Override
    public Optional<Customer> findByUsername(Username username) {
        return Optional.empty();
    }

    @Override
    public Customer findByCustomerId(MecanographicNumber mecanographicNumber) {
        TypedQuery<Customer> query = super.createQuery("SELECT c FROM Customer c WHERE c.customerId = :customerId", Customer.class);
        query.setParameter("customerId", mecanographicNumber);
        return query.getSingleResult();
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
