package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.order.domain.OrderID;
import eapli.base.ordersmanagement.order.domain.Status;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;

public class JpaOrderRepository extends JpaAutoTxRepository<ProductOrder, String, String> implements OrderRepository {


    public JpaOrderRepository(TransactionalContext autoTx) {
        super(autoTx, "orderID");
    }

    public JpaOrderRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "orderID");
    }

    @Override
    public List<ProductOrder> findAllOrders() {
        TypedQuery<ProductOrder> query = super.createQuery("SELECT DISTINCT c FROM ProductOrder c", ProductOrder.class);
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public List<ProductOrder> findOrdersByStatus(Status status) {
        Query q = entityManager().createQuery("SELECT ord FROM ProductOrder ord " +
                " WHERE ord.status = :status");
        q.setParameter("status", status);
        return (q.getResultList());
    }

    @Override
    public List<ProductOrder> findOpenOrders(Status status, Customer customer) {
        Query q = entityManager().createQuery("SELECT ord FROM ProductOrder ord " +
                " WHERE ord.status != :status AND ord.customer = :customer");
        q.setParameter("status", status);
        q.setParameter("customer", customer);
        return (q.getResultList());
    }

    @Override
    public ProductOrder findByOrderID(String orderID) {
        Query q = entityManager().createQuery("SELECT ord FROM ProductOrder ord " +
                " WHERE ord.orderID.orderIden = :orderID");
        q.setParameter("orderID", orderID);
        return (ProductOrder) q.getSingleResult();

    }

    @Override
    public ProductOrder findOrderByAGV(String ident) {
        Query q = entityManager().createQuery(
                "SELECT order FROM AGV a "+
                " WHERE a.identifier.identifier = :ident");
            q.setParameter("ident", ident);
        return (ProductOrder) q.getSingleResult();

    }

    @Override
    public Optional<ProductOrder> ofIdentity(OrderID id) {
        return Optional.empty();
    }

    @Override
    public void deleteOfIdentity(OrderID entityId) {
    }

}
