package eapli.base.persistence.impl.inmemory;

import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.product.domain.Brand;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.base.ordersmanagement.product.domain.ShortDescription;
import eapli.base.ordersmanagement.product.domain.UniqueInternalCode;
import eapli.base.ordersmanagement.product.repositories.ProductRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.List;
import java.util.Optional;

public class InMemoryProductRepository extends InMemoryDomainRepository<Product, UniqueInternalCode> implements ProductRepository {

        static {
            InMemoryInitializer.init();
        }

    @Override
    public Optional<Product> findProduct(Brand brand, ShortDescription description, Category category) {
            return null;
        //return (matchOne(e -> e.getBrand().equals(brand))&&  matchOne(e -> e.getShortDescription().equals(description))&&  matchOne(e -> e.getCategory().equals(category)));
    }

    @Override
    public Optional<Product> findByBrand(Brand brand) {
        return matchOne(e -> e.getBrand().equals(brand));
    }

    @Override
    public Optional<Product> findByDescription(ShortDescription description) {
        return matchOne(e -> e.getShortDescription().equals(description));
    }


    @Override
    public Optional<Product> findByCategory(Category category) {
        return matchOne(e -> e.getCategory().equals(category));
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

