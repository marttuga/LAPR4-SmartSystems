package eapli.base.ordersmanagement.category.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.category.domain.CategoryCode;
import eapli.base.ordersmanagement.category.domain.CategoryDescription;
import eapli.base.ordersmanagement.category.repository.CategoryRepository;
import eapli.base.ordersmanagement.customer.repositories.CustomerRepository;
import eapli.base.ordersmanagement.product.domain.Brand;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.List;

public class DefineCategoryController {
    private final TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();
    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private final CategoryRepository categoryRepository = PersistenceContext.repositories().category();

    public Category defineCategory(CategoryCode categoryCode, CategoryDescription categoryDescription){
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.SALES_CLERK_USER);
        txCtx.beginTransaction();
        final Category category = new Category(categoryCode,categoryDescription);
        txCtx.commit();
        return category;
    }

    public Category findByCategoryCode(CategoryCode category) {
        return categoryRepository.findByCategoryCode(category);
    }
    public List<Category> findAllCategories() {
        return categoryRepository.findAllCategories();
    }
}
