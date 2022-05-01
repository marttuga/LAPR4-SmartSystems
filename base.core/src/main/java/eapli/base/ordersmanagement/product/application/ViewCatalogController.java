package eapli.base.ordersmanagement.product.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.category.application.DefineCategoryController;
import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.category.domain.CategoryCode;
import eapli.base.ordersmanagement.product.domain.Brand;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.base.ordersmanagement.product.domain.ShortDescription;
import eapli.base.ordersmanagement.product.domain.UniqueInternalCode;
import eapli.base.ordersmanagement.product.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

public class ViewCatalogController {
    private final ProductRepository productRepository = PersistenceContext.repositories().products();
    private final ViewCatalogService viewCatalogService = new ViewCatalogService();
    private static final ProductSorter productSorter = new ProductSorter();
    private final DefineCategoryController categoryController = new DefineCategoryController();

    public List<Product> getProductByBrand(Brand brand) {
        return productRepository.findByBrand(brand);
    }

    public List<Product> getProductByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getProductByDescription(ShortDescription shortDescription) {
        return productRepository.findByDescription(shortDescription);
    }

    public List<Product> getProductByBrandCategory(Brand brand, Category category) {
        return productRepository.findByBrandCategory(brand, category);
    }

    public List<Product> getProductByCategoryDescription(Category category, ShortDescription shortDescription) {
        return productRepository.findByDescriptionCategory(shortDescription, category);
    }

    public List<Product> getProductByBrandDescription(Brand brand, ShortDescription shortDescription) {
        return productRepository.findByBrandDescription(brand, shortDescription);
    }

    public List<Product> getProductByBrandDescriptionCategory(Brand brand, ShortDescription shortDescription, Category category) {
        return productRepository.findByBrandDescriptionCategory(brand, shortDescription, category);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAllProducts();
    }

    public List<Brand> findAllBrands() {
        return productRepository.findAllBrands();
    }

    public Product findByProductCode(UniqueInternalCode code) {
        return productRepository.findByProductCode(code);
    }

    public Brand findByBrandName(String brandName) {
        return productRepository.findByBrandName(brandName);
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

    public void printBrandsList(List<Brand> brandsList) {
        viewCatalogService.printBrandsList(brandsList);
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


    public void printCategoriesList(String categoryCode) {
        CategoryCode code = new CategoryCode(categoryCode);
        Category category = categoryController.findByCategoryCode(code);
        printHeader();
        List<Product> productCategoryList = getProductByCategory(category);
        printProductsList(productCategoryList);
    }

    public void printBrandList(String brandName) {
        Brand brand = findByBrandName(brandName);
        printHeader();
        List<Product> productBrandList = getProductByBrand(brand);
        printProductsList(productBrandList);
    }

    public void printDescriptionList(String description) {
        ShortDescription shortDescription = findByShortDescription(description);
        ShortDescription d = findByShortDescription(description);
        printHeader();
        List<Product> productList = getProductByDescription(d);
        printProductsList(productList);
    }

    public void printOrderedDescription() {
        printHeader();
        List<Product> producttList = findAllProducts();
        sortByDescription(producttList);
        printProductsList(producttList);
    }

    public void printOrderedPrice() {
        printHeader();
        List<Product> catalogueList = findAllProducts();
        sortByPrice(catalogueList);
        printProductsList(catalogueList);
    }

}
