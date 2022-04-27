package eapli.base.ordersmanagement.product.application;


import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.product.domain.*;
import eapli.base.ordersmanagement.product.repositories.ProductRepository;
import eapli.base.warehousemanagement.domain.*;
import eapli.framework.application.ApplicationService;


@ApplicationService
public class ProductService {

    private final ProductRepository productRepository = PersistenceContext.repositories().products();

    public Product createProduct(UniqueInternalCode uniqueInternalCode, ShortDescription shortDescription, ExtendedDescription extendedDescription, TechnicalDescription technicalDescription, Brand brand, Reference reference, ProductionCode productionCode, Weight weight , Barcode barcode, ProductPriceDetail priceDetail, Category category, Dimension dimension, WarehouseID warehouseID, Aisle aisle, Row row, Shelf shelf, Bin bin) {

        final ProductBuilder productBuilder = new ProductBuilder();
        Product product;
        product = productBuilder.build();
        productBuilder.withUniqueInternalCode(uniqueInternalCode).withShortDescription(shortDescription).withExtendedDescription(extendedDescription).withTechnicalDescription(technicalDescription).withBrand(brand).withReference(reference).withProductionCode(productionCode).withWeight(weight).withBarcode(barcode).withPriceDetail(priceDetail).withCategory(category).withDimension(dimension).withWarehouseID(warehouseID).withAisle(aisle).withRow(row).withShelf(shelf).withBin(bin) ;
        this.productRepository.save(product);

        return product;
    }
}
