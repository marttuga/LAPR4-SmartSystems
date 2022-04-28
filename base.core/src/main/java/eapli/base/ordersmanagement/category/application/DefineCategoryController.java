package eapli.base.ordersmanagement.category.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.category.domain.CategoryCode;
import eapli.base.ordersmanagement.category.repository.CategoryRepository;
import eapli.base.ordersmanagement.customer.repositories.CustomerRepository;
import eapli.base.ordersmanagement.product.domain.Brand;
import eapli.framework.domain.repositories.TransactionalContext;

import java.util.List;

public class DefineCategoryController {
    private final TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();

    private final CategoryRepository categoryRepository = PersistenceContext.repositories().category();

    public Category findByCategoryCode(CategoryCode category) {
        return categoryRepository.findByCategoryCode(category);
    }
    public List<Category> findAllCategories() {
        return categoryRepository.findAllCategories();
    }
}
