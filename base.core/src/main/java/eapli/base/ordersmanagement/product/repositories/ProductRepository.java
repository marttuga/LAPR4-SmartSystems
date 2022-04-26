package eapli.base.ordersmanagement.product.repositories;

import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.product.domain.Brand;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.base.ordersmanagement.product.domain.ShortDescription;
import eapli.base.ordersmanagement.product.domain.UniqueInternalCode;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface ProductRepository extends DomainRepository<UniqueInternalCode, Product> {
    Optional<Product> findProduct(Brand brand, ShortDescription description, Category category);

    Optional<Product> findByBrand(Brand brand);

    Optional<Product> findByDescription(ShortDescription description);

    Optional<Product> findByCategory(Category category);

}

