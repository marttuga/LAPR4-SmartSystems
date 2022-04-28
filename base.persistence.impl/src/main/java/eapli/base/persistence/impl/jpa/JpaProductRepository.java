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
        TypedQuery<Brand> query = super.createQuery("SELECT DISTINCT c FROM Brand c", Brand.class);
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public Product findByProductCode(UniqueInternalCode code) {
        TypedQuery<Product> query = super.createQuery("SELECT c FROM Prduct c WHERE c.code = :product_code", Product.class);
        query.setParameter("product_code", code);
        return query.getSingleResult();
    }

    @Override
    public Brand findByBrandName(String brandNome) {
        TypedQuery<Brand> query = super.createQuery("SELECT c FROM Brand c WHERE c.brandName = :brand_name", Brand.class);
        query.setParameter("brand_name", brandNome);
        return query.getSingleResult();
    }

    @Override
    public ShortDescription findByShortDescription(String shortDescription) {
        TypedQuery<ShortDescription> query = super.createQuery("SELECT c FROM ShortDescription c WHERE c.shortDescription = :shortDescription", ShortDescription.class);
        query.setParameter("shortDescription", shortDescription);
        return query.getSingleResult();
    }

    @Override
    public List<Product> findByBrand(Brand brand) {
        TypedQuery<Product> query = super.createQuery("SELECT c FROM Product c WHERE c.brand = :brand_name", Product.class);
        query.setParameter("brand_name", brand);
        return query.getResultList();
    }

    @Override
    public List<Product> findByDescription(ShortDescription description) {
        TypedQuery< Product> query = super.createQuery("SELECT c FROM Product c WHERE c.description = :shortDescription", Product.class);
        query.setParameter("shortDescription", description);
        return query.getResultList();
    }

    @Override
    public List<Product> findByCategory(Category category) {
        TypedQuery< Product> query = super.createQuery("SELECT c FROM Product c WHERE c.category = :product_category", Product.class);
        query.setParameter("product_category", category);
        return query.getResultList();
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
