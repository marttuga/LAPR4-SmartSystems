package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.category.domain.CategoryCode;
import eapli.base.ordersmanagement.category.repository.CategoryRepository;
import eapli.base.ordersmanagement.product.domain.Brand;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class JpaCategoryRepository extends JpaAutoTxRepository<Category, CategoryCode, CategoryCode> implements CategoryRepository {
    public JpaCategoryRepository(TransactionalContext autoTx) {
        super(autoTx, "categoryCode");
    }

    public JpaCategoryRepository(String puname){
        super(puname, Application.settings().getExtendedPersistenceProperties(), "warehouseID");
    }

    public Category findByCategoryCode(CategoryCode category) {
            TypedQuery<Category> query = super.createQuery("SELECT c FROM Category c WHERE c.category = :category_code", Category.class);
        query.setParameter("category_code", category);
        return query.getSingleResult();
    }

    @Override
    public List<Category> findAllCategories() {
        TypedQuery<Category> query = super.createQuery("SELECT DISTINCT c FROM Category c", Category.class);
        return new ArrayList<>(query.getResultList());
    }
}
