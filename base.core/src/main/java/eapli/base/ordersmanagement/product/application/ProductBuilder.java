package eapli.base.ordersmanagement.product.application;


import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.category.domain.CategoryCode;
import eapli.base.ordersmanagement.product.domain.*;
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


    private CategoryCode categoryCode;


    private Dimension dimension;

    private int aisleId;

    private int rowId;

    private int shelfId;


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

    public ProductBuilder withCategory(final CategoryCode categoryCode) {
        this.categoryCode = categoryCode;
        return this;
    }

    public ProductBuilder withDimension(final Dimension dimension) {
        this.dimension = dimension;
        return this;
    }

    public ProductBuilder withAisleId(final int aisleId) {
        this.aisleId = aisleId;
        return this;
    }

    public ProductBuilder withRowId(final int rowId) {
        this.rowId = rowId;
        return this;
    }

    public ProductBuilder withShelfId(final int shelfId) {
        this.shelfId = shelfId;
        return this;
    }



    public Product build() {
        return new Product(uniqueInternalCode,shortDescription,extendedDescription,technicalDescription,brand,reference,productionCode,weight,barcode,priceDetail,categoryCode,dimension,aisleId,rowId,shelfId);
    }
    public Product buildMandatory() {
        return new Product(uniqueInternalCode,shortDescription,extendedDescription,barcode);
    }

/*    public Product build() {
        return new Product(uniqueInternalCode,shortDescription,extendedDescription,technicalDescription,brand,reference,productionCode,weight,barcode,priceDetail,dimension);
    }*/
}
