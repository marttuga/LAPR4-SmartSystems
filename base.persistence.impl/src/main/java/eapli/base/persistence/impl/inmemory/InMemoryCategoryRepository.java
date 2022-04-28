package eapli.base.persistence.impl.inmemory;

import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.category.domain.CategoryCode;
import eapli.base.ordersmanagement.category.repository.CategoryRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.List;

public class InMemoryCategoryRepository extends InMemoryDomainRepository<Category, CategoryCode>
        implements CategoryRepository {
    static {
        InMemoryInitializer.init();

    }

    @Override
    public Category findByCategoryCode(CategoryCode category) {
        return null;
    }

    @Override
    public List<Category> findAllCategories() {
        return null;
    }
}
