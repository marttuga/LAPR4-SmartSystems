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


import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;

public class JpaProductRepository extends JpaAutoTxRepository<Product, String, String> implements ProductRepository {

    public JpaProductRepository(TransactionalContext autoTx) {
        super(autoTx, "uniqueInternalCode");
    }

    public JpaProductRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "uniqueInternalCode");
    }


    @Override
    public List<Product> findAllProducts() {
        TypedQuery<Product> query = super.createQuery("SELECT DISTINCT c FROM Product c", Product.class);
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public List<Brand> findAllBrands() {
        TypedQuery<Brand> query = super.createQuery("SELECT DISTINCT brandName FROM Product c", Brand.class);
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public Product findByProductCode(String uniqueinternalcode) {
        Query q = entityManager().createQuery("SELECT prod FROM Product prod " +
                " WHERE prod.uniqueInternalCode.uniqueInternalCode = :uniqueinternalcode");
        q.setParameter("uniqueinternalcode", uniqueinternalcode);
        return (Product) q.getSingleResult();

    }

    @Override
    public Brand findByBrandName(String brandName) {
        Query q = entityManager().createQuery("SELECT prod FROM Product prod " +
                " WHERE prod.brand.brandName = :brandName");
        q.setParameter("brandName", brandName);
        return (Brand) q.getSingleResult();

    }

    @Override
    public ShortDescription findByShortDescription(String shortDescription) {
        Query q = entityManager().createQuery("SELECT prod FROM Product prod " +
                " WHERE prod.shortDescription.description = :shortDescription");
        q.setParameter("shortDescription", shortDescription);
        return (ShortDescription) q.getSingleResult();
    }

    @Override
    public List<Product> findByBrand(String brandName) {
            Query q = entityManager().createQuery("SELECT prod FROM Product prod " +
                    " WHERE prod.brand.brandName = :brandName");
            q.setParameter("brandName", brandName);
            return q.getResultList();
    }

    @Override
    public List<Product> findByDescription(String shortDescription) {
        Query q = entityManager().createQuery("SELECT prod FROM Product prod " +
                " WHERE prod.shortDescription.description = :shortDescription");
        q.setParameter("shortDescription", shortDescription);
        return  q.getResultList();
    }

    @Override
    public List<Product> findByCategory(String categorycode) {
        Query q = entityManager().createQuery("SELECT prod FROM Product prod " +
                " WHERE prod.categoryCode.categoryCode = :categoryCode");
        q.setParameter("categoryCode", categorycode);
        return  q.getResultList();
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
        TypedQuery<Product> query = super.createQuery("SELECT c FROM Product c WHERE ( c.brand = :productBrand AND c.categorycode =:productCategory", Product.class);
        query.setParameter("productBrand", brand);
        query.setParameter("productCategory", category);
        return query.getResultList();
    }

    @Override
    public List<Product> findByDescriptionCategory(ShortDescription description, Category category) {
        TypedQuery<Product> query = super.createQuery("SELECT c FROM Product c WHERE ( c.description=:productDescription AND c.categorycode =:productCategory", Product.class);
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
