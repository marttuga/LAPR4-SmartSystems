package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.category.application.DefineCategoryController;
import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.category.repository.CategoryRepository;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.hibernate.boot.archive.scan.spi.ClassDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CategoryBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            ClassDescriptor.Categorization.class);
    private final DefineCategoryController defineCategoryController = new DefineCategoryController();
    private final CategoryRepository categoryRepository = PersistenceContext.repositories().category();

    @Override
    public boolean execute() {
        createCategory("555", "Camisolas");
        createCategory("556", "Calças");
        return true;
    }

    private void createCategory(String categoryCode, String categoryDescription) {

        Category category;
        try {
            category = defineCategoryController.defineCategory(categoryCode, categoryDescription);
            categoryRepository.save(category);
        } catch (final ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", categoryCode);
            LOGGER.trace("Assuming existing record", e);
        }
    }
}
