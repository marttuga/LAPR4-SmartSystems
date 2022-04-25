package eapli.base.persistence.impl.jpa;


import eapli.base.Application;
import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.product.domain.Brand;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.base.ordersmanagement.product.domain.ShortDescription;
import eapli.base.ordersmanagement.product.domain.UniqueInternalCode;
import eapli.base.ordersmanagement.product.repositories.ProductRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;


import java.util.Optional;

public class JpaProductRepository extends JpaAutoTxRepository<Product, String, String> implements ProductRepository {

    public JpaProductRepository(TransactionalContext autoTx) {
        super(autoTx, "uniqueInternalCode");
    }

    public JpaProductRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "uniqueInternalCode");
    }

    @Override
    public Optional<Product> findProduct(Brand brand, ShortDescription description, Category category) {
        return Optional.empty();
    }

    @Override
    public Iterable<Brand> findProductsByBrand(Brand brand) {
        return null;
    }

    @Override
    public Iterable<ShortDescription> findByDescription(ShortDescription description) {
        return null;
    }

    @Override
    public Iterable<Category> findByCategory(Category category) {
        return null;
    }


    @Override
    public Optional<Product> ofIdentity(UniqueInternalCode id) {
        return Optional.empty();
    }

    @Override
    public void deleteOfIdentity(UniqueInternalCode entityId) {
    }
}
