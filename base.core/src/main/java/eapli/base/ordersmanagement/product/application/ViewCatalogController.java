package eapli.base.ordersmanagement.product.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.product.domain.Brand;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.base.ordersmanagement.product.domain.ShortDescription;
import eapli.base.ordersmanagement.product.repositories.ProductRepository;

import java.util.Optional;

public class ViewCatalogController {
    private final ProductRepository productRepository = PersistenceContext.repositories().products();

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductByBrand(Brand brand) {
        return productRepository.findByBrand(brand);
    }
    public Optional<Product> getProductByCategory(Category category) {
        return productRepository.findByCategory(category);
    }
    public Optional<Product> getProductByDescription(ShortDescription shortDescription) {
        return productRepository.findByDescription(shortDescription);
    }


}
