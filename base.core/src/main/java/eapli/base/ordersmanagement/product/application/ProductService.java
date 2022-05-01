package eapli.base.ordersmanagement.product.application;


import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.category.domain.CategoryCode;
import eapli.base.ordersmanagement.product.domain.*;
import eapli.base.ordersmanagement.product.repositories.ProductRepository;
import eapli.framework.application.ApplicationService;


@ApplicationService
public class ProductService {

    private final ProductRepository productRepository = PersistenceContext.repositories().products();

    public Product createProduct(UniqueInternalCode uniqueInternalCode, ShortDescription shortDescription, ExtendedDescription extendedDescription, TechnicalDescription technicalDescription, Brand brand, Reference reference, ProductionCode productionCode, Weight weight , Barcode barcode, ProductPriceDetail priceDetail, CategoryCode categoryCode, Dimension dimension, int aisleId, int rowId, int shelfId) {

        final ProductBuilder productBuilder = new ProductBuilder();
        Product product;
        product = productBuilder.build();
        productBuilder.withUniqueInternalCode(uniqueInternalCode).withShortDescription(shortDescription).withExtendedDescription(extendedDescription).withTechnicalDescription(technicalDescription).withBrand(brand).withReference(reference).withProductionCode(productionCode).withWeight(weight).withBarcode(barcode).withPriceDetail(priceDetail).withCategory(categoryCode).withDimension(dimension).withAisleId(aisleId).withRowId(rowId).withShelfId(shelfId) ;
        this.productRepository.save(product);

        return product;
    }

/*        public Product createProduct(UniqueInternalCode uniqueInternalCode, ShortDescription shortDescription, ExtendedDescription extendedDescription, TechnicalDescription technicalDescription, Brand brand, Reference reference, ProductionCode productionCode, Weight weight , Barcode barcode, ProductPriceDetail priceDetail, Dimension dimension) {

        final ProductBuilder productBuilder = new ProductBuilder();
        Product product;
        product = productBuilder.build();
        productBuilder.withUniqueInternalCode(uniqueInternalCode).withShortDescription(shortDescription).withExtendedDescription(extendedDescription).withTechnicalDescription(technicalDescription).withBrand(brand).withReference(reference).withProductionCode(productionCode).withWeight(weight).withBarcode(barcode).withPriceDetail(priceDetail).withDimension(dimension);
        this.productRepository.save(product);

        return product;
    }*/

        public Product createProductMandatory(UniqueInternalCode uniqueInternalCode, ShortDescription shortDescription, ExtendedDescription extendedDescription) {

        final ProductBuilder productBuilder = new ProductBuilder();
        Product product;
        product = productBuilder.buildMandatory();
        productBuilder.withUniqueInternalCode(uniqueInternalCode).withShortDescription(shortDescription).withExtendedDescription(extendedDescription);
        this.productRepository.save(product);

        return product;
    }
}
