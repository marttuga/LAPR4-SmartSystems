package eapli.base.ordersmanagement.product.domain;


import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.warehousemanagement.domain.*;
import eapli.framework.application.ApplicationService;

@ApplicationService
public class ProductBuilder {

    private UniqueInternalCode uniqueInternalCode;


    private ShortDescription shortDescription;


    private ExtendedDescription extendedDescription;


    private TechnicalDescription technicalDescription;


    private Brand brand;


    private Reference reference;


    private ProductionCode productionCode;


    private Weight weight;


    private Barcode barcode;


    private ProductPriceDetail priceDetail;


    private Category category;


    private Dimension dimension;


    private WarehouseID warehouseID;


    private Aisle aisle;


    private Row row;


    private Shelf shelf;


    private Bin bin;


    public ProductBuilder withUniqueInternalCode(final UniqueInternalCode uniqueInternalCode) {
        this.uniqueInternalCode = uniqueInternalCode;
        return this;
    }

    public ProductBuilder withShortDescription(final ShortDescription shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    public ProductBuilder withExtendedDescription(final ExtendedDescription extendedDescription) {
        this.extendedDescription = extendedDescription;
        return this;
    }

    public ProductBuilder withTechnicalDescription(final TechnicalDescription technicalDescription) {
        this.technicalDescription = technicalDescription;
        return this;
    }

    public ProductBuilder withBrand(final Brand brand) {
        this.brand = brand;
        return this;
    }

    public ProductBuilder withReference(final Reference reference) {
        this.reference = reference;
        return this;
    }

    public ProductBuilder withProductionCode(final ProductionCode productionCode) {
        this.productionCode = productionCode;
        return this;
    }

    public ProductBuilder withWeight(final Weight weight) {
        this.weight = weight;
        return this;
    }

    public ProductBuilder withBarcode(final Barcode barcode) {
        this.barcode = barcode;
        return this;
    }

    public ProductBuilder withPriceDetail(final ProductPriceDetail priceDetail) {
        this.priceDetail = priceDetail;
        return this;
    }

    public ProductBuilder withCategory(final Category category) {
        this.category = category;
        return this;
    }

    public ProductBuilder withDimension(final Dimension dimension) {
        this.dimension = dimension;
        return this;
    }

    public ProductBuilder withWarehouseID(final WarehouseID warehouseID) {
        this.warehouseID = warehouseID;
        return this;
    }

   public ProductBuilder withAisle(final Aisle aisle) {
        this.aisle = aisle;
        return this;
    }
    public ProductBuilder withRow(final Row row) {
        this.row = row;
        return this;
    }
    public ProductBuilder withShelf(final Shelf shelf) {
        this.shelf = shelf;
        return this;
    }
    public ProductBuilder withBin(final Bin bin) {
        this.bin = bin;
        return this;
    }

    public Product build() {
        return new Product(uniqueInternalCode,shortDescription,extendedDescription,technicalDescription,brand,reference,productionCode,weight,barcode,priceDetail,category,dimension,warehouseID,aisle,row,shelf,bin);
    }
}
