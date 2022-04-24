package eapli.base.catalog.repositories;

public interface CatalogRepository extends DomainRepository<InternalCode, Product> {


    Optional<Product> findByUserForADayAndMealType(Brand brand, Description description, Category category);


    Iterable<Brand> findProductsByBrand(Brand brand);
    Iterable<Description> findByDescription(Description description);
    Iterable<Category> findByCategory (Category category);


}
