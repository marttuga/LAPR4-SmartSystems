package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.category.domain.CategoryCode;
import eapli.base.ordersmanagement.product.application.SpecifyNewProductController;
import eapli.base.ordersmanagement.product.domain.*;
import eapli.base.ordersmanagement.product.repositories.ProductRepository;
import eapli.base.warehousemanagement.domain.Aisle;
import eapli.base.warehousemanagement.domain.Row;
import eapli.base.warehousemanagement.domain.Shelf;
import eapli.base.warehousemanagement.domain.WarehouseID;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductBootstrapper.class);
    private final SpecifyNewProductController specifyNewProductController = new SpecifyNewProductController();
    private final ProductRepository productRepository = PersistenceContext.repositories().products();

    @Override
    public boolean execute() {
        createProduct("555", "shortDescription1", "longDescription1",
                "technicalDescription1", "Levs","test", "222", 10,
                "2222222222222", "10", "555", 100, 100, 100,
                1, 1, 1);
        createProduct("556", "shortDescription2", "longDescription2",
                "technicalDescription2", "Supreme","test", "222", 10,
                "2222222222223", "10", "555", 100, 100, 100,
                2, 2, 2);
        createProduct("557", "shortDescription3", "longDescription3",
                "technicalDescription3", "Perry Fred","test", "222", 10,
                "2222222222224", "10", "556", 100, 100, 100,
                3, 3, 3);
        createProduct("558", "shortDescription4", "longDescription4",
                "technicalDescription4", "Levs","test", "222", 10,
                "2222222222225", "10", "556", 100, 100, 100,
                4, 4, 4);

        return true;
    }

    private void createProduct(String uniqueInternalCode,
                                String shortDescription,
                                String extendedDescription,
                                String technicalDescription,
                                String brand,
                                String reference,
                                String productionCode, int weight, String barcode, String priceDetail, String categoryCode,
                               double width, double heigth, double length, int aisle, int row, int shelf) {

        Product product;
        try {
            Dimension dimension = new Dimension(width, heigth, length);
            product = specifyNewProductController.specifyNewProduct(new UniqueInternalCode(uniqueInternalCode), new ShortDescription(shortDescription),
                    new ExtendedDescription(extendedDescription), new TechnicalDescription(technicalDescription),
                    new Brand(brand),  new Reference(reference), new ProductionCode(productionCode), new Weight(weight), new Barcode(barcode),
                    new ProductPriceDetail(priceDetail), new CategoryCode(categoryCode), dimension,
                    aisle, row, shelf);
            productRepository.save(product);
        } catch (final ConcurrencyException | IntegrityViolationException | IllegalAccessException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", productionCode);
            LOGGER.trace("Assuming existing record", e);
        }
    }
}
