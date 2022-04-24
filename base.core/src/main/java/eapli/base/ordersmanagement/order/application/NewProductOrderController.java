package eapli.base.ordersmanagement.order.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;

public class NewProductOrderController {

    private final OrderRepository catalogueRepository = PersistenceContext.repositories().orders();
}
