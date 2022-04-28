package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.category.domain.CategoryCode;
import eapli.base.ordersmanagement.category.repository.CategoryRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaCategoryRepository extends JpaAutoTxRepository<Category, CategoryCode, CategoryCode> implements CategoryRepository {
    public JpaCategoryRepository(TransactionalContext autoTx) {
        super(autoTx, "categoryCode");
    }

    public JpaCategoryRepository(String puname){
        super(puname, Application.settings().getExtendedPersistenceProperties(), "warehouseID");
    }
}
