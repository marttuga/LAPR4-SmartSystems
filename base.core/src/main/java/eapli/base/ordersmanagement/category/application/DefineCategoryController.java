package eapli.base.ordersmanagement.category.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.category.repository.CategoryRepository;
import eapli.base.ordersmanagement.customer.repositories.CustomerRepository;
import eapli.framework.domain.repositories.TransactionalContext;

public class DefineCategoryController {
    private final TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();

    private final CategoryRepository categoryRepository = PersistenceContext.repositories().category();
}
