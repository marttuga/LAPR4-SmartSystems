package eapli.base.ordersmanagement.category.repository;

import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.category.domain.CategoryCode;
import eapli.base.ordersmanagement.product.domain.Brand;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface CategoryRepository extends DomainRepository<CategoryCode, Category> {

    Category findByCategoryCode(CategoryCode category);
    List<Category> findAllCategories();
}
