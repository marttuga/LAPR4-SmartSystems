package eapli.base.ordersmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.repositories.OrderRepository;

public class NewProductOrderController {

    private final OrderRepository catalogueRepository = PersistenceContext.repositories().catalogues();
}
