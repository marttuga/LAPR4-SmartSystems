package eapli.base.catalog.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Brand implements ValueObject, Comparable<Brand> {
    public interface ProductsCatalogRepository extends DomainRepository<InternalCode, Product> {


        Optional<Product> findByUserForADayAndMealType(Brand brand, Description description, Category category);


        Iterable<Brand> findProductsByBrand(Brand brand);
        Iterable<Description> findByDescription(Description description);
        Iterable<Category> findByCategory (Category category);


    }
}
