package eapli.base.ordersmanagement.product.domain;

import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.category.domain.CategoryCode;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;

/**
 *
 * @author João Tiago Araújo <<1200584@isep.ipp.pt>>
 */

@Entity
public class Product implements AggregateRoot<UniqueInternalCode> {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UniqueInternalCode uniqueInternalCode;

    @Embedded
    private ShortDescription shortDescription;

    @Embedded
    private ExtendedDescription extendedDescription;

    @Embedded
    private TechnicalDescription technicalDescription;

    @Embedded
    private Brand brand;

    @Embedded
    private Reference reference;

    @Embedded
    private ProductionCode productionCode;

    @Column(insertable = false, updatable = false)
    @Embedded
    private Weight weight;

    @Embedded
    private Barcode barcode;

    @Embedded
    private ProductPriceDetail priceDetail;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Category category;

    private CategoryCode categoryCode;

    @Embedded
    private Dimension dimension;

    private int aisleId;

    private int rowId;

    private int shelfId;

/*    @Lob
    private byte[] picture;*/

    public int getAisleId() {
        return aisleId;
    }

    public int getRowId() {
        return rowId;
    }

    public int getShelfId() {
        return shelfId;
    }

    public CategoryCode getCategoryCode() {
        return categoryCode;
    }

    public Product(UniqueInternalCode uniqueInternalCode, ShortDescription shortDescription, ExtendedDescription extendedDescription, TechnicalDescription technicalDescription, Brand brand, Reference reference, ProductionCode productionCode, Weight weight, Barcode barcode, ProductPriceDetail priceDetail, CategoryCode categoryCode, Dimension dimension, int aisleId, int rowId, int shelfId) {
        this.uniqueInternalCode = uniqueInternalCode;
        this.shortDescription = shortDescription;
        this.extendedDescription = extendedDescription;
        this.technicalDescription = technicalDescription;
        this.brand = brand;
        this.reference = reference;
        this.productionCode = productionCode;
        this.weight = weight;
        this.barcode = barcode;
        this.priceDetail = priceDetail;
        this.categoryCode = categoryCode;
        this.dimension = dimension;
        this.aisleId = aisleId;
        this.rowId = rowId;
        this.shelfId = shelfId;
    }

    public Product(UniqueInternalCode uniqueInternalCode, int aisleId) {
        this.uniqueInternalCode = uniqueInternalCode;
        this.aisleId = aisleId;
    }

    public Product(UniqueInternalCode uniqueInternalCode, ShortDescription shortDescription, ExtendedDescription extendedDescription, Barcode barcode, CategoryCode categoryCode) {
        this.uniqueInternalCode = uniqueInternalCode;
        this.shortDescription = shortDescription;
        this.extendedDescription = extendedDescription;
        this.barcode = barcode;
        this.categoryCode = categoryCode;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Warehouse warehouse;


    protected Product() {

    }

/*
    public byte[] picture() {
        return Arrays.copyOf(picture, picture.length);
    }

    public boolean hasPicture() {
        return picture != null && picture.length != 0;
    }


    public void changePicture(final byte[] picture) {
        this.picture = Arrays.copyOf(picture, picture.length);
    }
*/

    public static int valueOfAisleId(final int aisleId) throws IllegalAccessException {
        return aisleId;
    }

    public static int valueOfRowId(final int rowId) throws IllegalAccessException {
        return rowId;
    }

    public static int valueOfShelfId(final int shelfId) throws IllegalAccessException {
        return shelfId;
    }

    @Override
    public int compareTo(UniqueInternalCode other) {
        return AggregateRoot.super.compareTo(other);
    }

    public UniqueInternalCode uniqueInternalCode() {
        return identity();
    }

    public UniqueInternalCode getUniqueInternalCode() {
        return uniqueInternalCode;
    }

    @Override
    public UniqueInternalCode identity() {
        return this.uniqueInternalCode;
    }

    @Override
    public boolean hasIdentity(UniqueInternalCode id) {
        return AggregateRoot.super.hasIdentity(id);
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    public Brand getBrand() {
        return brand;
    }

    public ShortDescription getShortDescription() {
        return shortDescription;
    }

    public ExtendedDescription getExtendedDescription() {
        return extendedDescription;
    }

    public TechnicalDescription getTechnicalDescription() {
        return technicalDescription;
    }

    public Reference getReference() {
        return reference;
    }

    public ProductionCode getProductionCode() {
        return productionCode;
    }

    public Weight getWeight() {
        return weight;
    }

    public Barcode getBarcode() {
        return barcode;
    }

    public CategoryCode getCategory() {
        return categoryCode;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public ProductPriceDetail getPriceDetail() {
        return priceDetail;
    }

    @Override
    public String toString() {
        return "Product:" +
                "\nuniqueInternalCode=" + uniqueInternalCode +
                " | shortDescription=" + shortDescription +
                " | brand=" + brand +
                " | category=" + categoryCode +
                " | barcode=" + barcode +
                " | priceDetail=" + priceDetail;
    }
}