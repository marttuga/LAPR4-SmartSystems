package eapli.base.ordersmanagement.product.application;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.category.application.DefineCategoryController;
import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.base.ordersmanagement.product.domain.ShortDescription;
import eapli.base.ordersmanagement.product.repositories.ProductRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ViewCatalogController {
    private final ProductRepository productRepository = PersistenceContext.repositories().products();
    private final ViewCatalogService viewCatalogService = new ViewCatalogService();
    private static final ProductSorter productSorter = new ProductSorter();
    private final DefineCategoryController categoryController = new DefineCategoryController();

    public List<Product> getProductByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    public List<Product> getProductByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getProductByDescription(String shortDescription) {
        return productRepository.findByDescription(shortDescription);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAllProducts();
    }

    public Product findByProductCode(String code) {
        return productRepository.findByProductCode(code);
    }


    public ShortDescription findByShortDescription(String shortDescription) {
        return productRepository.findByShortDescription(shortDescription);
    }

    public int showOptionsFilter() {
        return viewCatalogService.showOptionsFilter();
    }

    public int showOptionsOrdering() {
        return viewCatalogService.showOptionsOrdering();
    }

    public void printProductsList(List<Product> productList) {
        viewCatalogService.printProductsList(productList);
    }

    public void printCategoriesList(List<Category> categoriesList) {
        viewCatalogService.printCategoriesList(categoriesList);
    }


    public List<Product> sortByPrice(List<Product> productList) {
        return productSorter.sortByPrice(productList);
    }

    public List<Product> sortByDescription(List<Product> productList) {
        return productSorter.sortByDescription(productList);
    }

    public void printHeader() {
        viewCatalogService.printHeader();
    }


    public List<Product> printCategoriesList(String categoryCode) {
        printHeader();
        List<Product> productCategoryList = getProductByCategory(categoryCode);
       printProductsList(productCategoryList);
       return productCategoryList;
    }

    public  List<Product> printBrandList(String brandName) {
        printHeader();
        List<Product> productBrandList = getProductByBrand(brandName);
        printProductsList(productBrandList);
        return productBrandList;
    }

    public List<Product> printDescriptionList(String description) {
        ShortDescription shortDescription = findByShortDescription(description);
        ShortDescription d = findByShortDescription(description);
        printHeader();
        List<Product> productList = getProductByDescription(description);
        printProductsList(productList);
        return productList;
    }

    public void printOrderedDescription(List<Product> producttList) {
        printHeader();
        sortByDescription(producttList);
        printProductsList(producttList);
    }

    public void printOrderedPrice(List<Product> catalogueList) {
        printHeader();
        sortByPrice(catalogueList);
        printProductsList(catalogueList);
    }

}
