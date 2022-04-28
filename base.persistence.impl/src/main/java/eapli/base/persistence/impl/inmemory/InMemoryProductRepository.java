package eapli.base.persistence.impl.inmemory;

import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.product.domain.Brand;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.base.ordersmanagement.product.domain.ShortDescription;
import eapli.base.ordersmanagement.product.domain.UniqueInternalCode;
import eapli.base.ordersmanagement.product.repositories.ProductRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import java.util.List;


public class InMemoryProductRepository extends InMemoryDomainRepository<Product, UniqueInternalCode> implements ProductRepository {

        static {
            InMemoryInitializer.init();
        }

    @Override
    public List<Product> findAllProducts() {
        return findAllProducts();
    }

    @Override
    public List<Brand> findAllBrands() {
        return findAllBrands();
    }

    @Override
    public Product findByProductCode(UniqueInternalCode code) {
        return findByProductCode(code);
    }

    @Override
    public List<Product> findByBrand(Brand brand) {
        return null;//matchOne(e -> e.getBrand().equals(brand));
    }

    @Override
    public Brand findByBrandName(String brandNome) {
        return findByBrandName(brandNome);
    }

    @Override
    public ShortDescription findByShortDescription(String shortDescription) {
        return findByShortDescription(shortDescription);
    }

    @Override
    public List<Product> findByDescription(ShortDescription description) {
        return null;//matchOne(e -> e.getShortDescription().equals(description));
    }


    @Override
    public List<Product> findByCategory(Category category) {
        return null;//(e -> e.getCategory().equals(category));
    }

    @Override
    public List<Product> findByBrandDescription(Brand brand, ShortDescription description) {
        return null;
    }

    @Override
    public List<Product> findByBrandCategory(Brand brand, Category category) {
        return null;
    }

    @Override
    public List<Product> findByDescriptionCategory(ShortDescription description, Category category) {
        return null;
    }

    @Override
    public List<Product> findByBrandDescriptionCategory(Brand brand, ShortDescription description, Category category) {
        return null;
    }


}

