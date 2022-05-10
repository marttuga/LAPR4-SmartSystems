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
import eapli.framework.general.domain.model.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductBootstrapper.class);
    private final SpecifyNewProductController specifyNewProductController = new SpecifyNewProductController();
    private final ProductRepository productRepository = PersistenceContext.repositories().products();

    @Override
    public boolean execute() {
        createProduct("555", "shortDescription1", "longDescription1",
                "technicalDescription1", "Levis","test", "222", 10,
                "2222222222222", Money.euros(10), "555", 100, 100, 100,
                1, 1, 1);
        createProduct("556", "shortDescription2", "longDescription2",
                "technicalDescription2", "Supreme","test", "222", 10,
                "2222222222223", Money.euros(20), "555", 100, 100, 100,
                2, 2, 2);
        createProduct("557", "shortDescription3", "longDescription3",
                "technicalDescription3", "Perry Fred","test", "222", 10,
                "2222222222224", Money.euros(30), "556", 100, 100, 100,
                3, 3, 3);
        createProduct("558", "shortDescription4", "longDescription4",
                "technicalDescription4", "Levis","test", "222", 10,
                "2222222222225", Money.euros(5), "556", 100, 100, 100,
                4, 4, 4);

        createProduct("559", "shortDescription5", "longDescription5",
                "technicalDescription5", "Levis","test", "222", 10,
                "2222222222226", Money.euros(35), "556", 100, 100, 100,
                5, 5, 5);

        createProduct("560", "shortDescription6", "longDescription6",
                "technicalDescription6", "zara home","test", "222", 17,
                "2222222222227", Money.euros(40), "557", 100, 100, 100,
                6, 6, 6);

        createProduct("561", "shortDescription7", "longDescription7",
                "technicalDescription7", "ikea","test", "222", 10,
                "2222222222228", Money.euros(10), "557", 100, 100, 100,
                7, 7, 7);

        createProduct("562", "shortDescription8", "longDescription8",
                "technicalDescription8", "milka","test", "222", 7,
                "2222222222229", Money.euros(5), "558", 4, 4, 10,
                8, 8, 8);

        createProduct("563", "shortDescription9", "longDescription9",
                "technicalDescription9", "milka","test", "222", 9,
                "2222222222230", Money.euros(5), "558", 4, 4, 10,
                9, 9, 9);

        return true;
    }

    private void createProduct(String uniqueInternalCode,
                               String shortDescription,
                               String extendedDescription,
                               String technicalDescription,
                               String brand,
                               String reference,
                               String productionCode, double weight, String barcode, Money priceDetail, String categoryCode,
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
