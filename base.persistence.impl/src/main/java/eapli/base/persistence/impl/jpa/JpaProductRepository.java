package eapli.base.persistence.impl.jpa;


import eapli.base.Application;
import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.customer.domain.CustomerId;
import eapli.base.ordersmanagement.product.domain.Brand;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.base.ordersmanagement.product.domain.ShortDescription;
import eapli.base.ordersmanagement.product.domain.UniqueInternalCode;
import eapli.base.ordersmanagement.product.repositories.ProductRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;


import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public Optional<Product> findByBrand(Brand brand) {
        final Map<String, Object> params = new HashMap<>();
        params.put("productBrand", brand);
        return matchOne("e.brand=:productBrand", params);
    }


    @Override
    public Optional<Product> findByDescription(ShortDescription description) {
        final Map<String, Object> params = new HashMap<>();
        params.put("productDescription", description);
        return matchOne("e.description=:productDescription", params);
    }

    @Override
    public Optional<Product> findByCategory(Category category) {
        final Map<String, Object> params = new HashMap<>();
        params.put("productCategory", category);
        return matchOne("e.category=:productCategory", params);
    }

    @Override
    public List<Product> findByBrandDescription(Brand brand, ShortDescription description) {
        TypedQuery<Product> query = super.createQuery("SELECT c FROM Product c WHERE ( c.brand = :productBrand AND c.description=:productDescription", Product.class);
        query.setParameter("productBrand", brand);
        query.setParameter("productDescription", description);
        return query.getResultList();
    }

    @Override
    public List<Product> findByBrandCategory(Brand brand, Category category) {
        TypedQuery<Product> query = super.createQuery("SELECT c FROM Product c WHERE ( c.brand = :productBrand AND c.category=:productCategory", Product.class);
        query.setParameter("productBrand", brand);
        query.setParameter("productCategory", category);
        return query.getResultList();
    }

    @Override
    public List<Product> findByDescriptionCategory(ShortDescription description, Category category) {
        TypedQuery<Product> query = super.createQuery("SELECT c FROM Product c WHERE ( c.description=:productDescription AND c.category=:productCategory", Product.class);
        query.setParameter("productDescription", description);
        query.setParameter("productCategory", category);
        return query.getResultList();
    }

    @Override
    public List<Product> findByBrandDescriptionCategory(Brand brand, ShortDescription description, Category category) {
        TypedQuery<Product> query = super.createQuery("SELECT c FROM Product c WHERE ( c.brand = :productBrand AND c.description=:productDescription AND c.category=:productCategory", Product.class);
        query.setParameter("productBrand", brand);
        query.setParameter("productDescription", description);
        query.setParameter("productCategory", category);
        return query.getResultList();
    }
    @Override
    public Optional<Product> ofIdentity(UniqueInternalCode id) {
        return Optional.empty();
    }

    @Override
    public void deleteOfIdentity(UniqueInternalCode entityId) {

    }
}
