package eapli.base.ordersmanagement.product.repositories;

import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.category.domain.CategoryCode;
import eapli.base.ordersmanagement.product.domain.Brand;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.base.ordersmanagement.product.domain.ShortDescription;
import eapli.base.ordersmanagement.product.domain.UniqueInternalCode;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends DomainRepository<UniqueInternalCode, Product> {
    List<Product> findAllProducts();

    List<Brand> findAllBrands();

    Product findByProductCode(String code);

    Brand findByBrandName(String brandNome);

    ShortDescription findByShortDescription(String shortDescription);

    List<Product> findByBrand(String brand);

    List<Product> findByDescription(String description);

    List<Product> findByCategory(String category);

    List<Product> findByBrandDescription(Brand brand, ShortDescription description);

    List<Product> findByBrandCategory(Brand brand, Category category);

    List<Product> findByDescriptionCategory(ShortDescription description, Category category);

    List<Product> findByBrandDescriptionCategory(Brand brand, ShortDescription description, Category category);
}

