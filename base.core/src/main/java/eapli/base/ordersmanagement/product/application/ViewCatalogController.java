package eapli.base.ordersmanagement.product.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.product.domain.Brand;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.base.ordersmanagement.product.domain.ShortDescription;
import eapli.base.ordersmanagement.product.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

public class ViewCatalogController {
    private final ProductRepository productRepository = PersistenceContext.repositories().products();

    public List<Product> getProductByBrand(Brand brand) {
        return productRepository.findByBrand(brand);
    }
    public List<Product> getProductByCategory(Category category) {return productRepository.findByCategory(category);}
    public List<Product> getProductByDescription(ShortDescription shortDescription) { return productRepository.findByDescription(shortDescription);}

    public List<Product> getProductByBrandCategory(Brand brand,Category category) {
        return productRepository.findByBrandCategory(brand,category);
    }
    public List<Product> getProductByCategoryDescription(Category category,ShortDescription shortDescription) {
        return productRepository.findByDescriptionCategory(shortDescription,category);
    }
    public List<Product> getProductByBrandDescription(Brand brand,ShortDescription shortDescription) {
        return productRepository.findByBrandDescription(brand,shortDescription);
    }
    public List<Product> getProductByBrandDescriptionCategory(Brand brand,ShortDescription shortDescription, Category category) {
        return productRepository.findByBrandDescriptionCategory(brand,shortDescription,category);
    }
    public List<Product> findAllProducts(){
        return productRepository.findAllProducts();
    }
    public List<Brand> findAllBrands(){
        return productRepository.findAllBrands();
    }
    public Product findByProductCode(String code){
        return productRepository.findByProductCode(code);
    }
    public Brand findByBrandName(String brandName){
        return productRepository.findByBrandName(brandName);
    }
    public ShortDescription findByShortDescription(String shortDescription){
        return productRepository.findByShortDescription(shortDescription);
    }
}
