package eapli.base.ordersmanagement.product.domain;

import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.category.domain.CategoryCode;
import eapli.base.warehousemanagement.domain.*;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.domain.WarehouseID;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;
import java.util.Arrays;

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
    private Category categoryCode;

    @Embedded
    private Dimension dimension;

    @Column(insertable = false,updatable = false)
    @Embedded
    private WarehouseID warehouseID;

    @Embedded
    private Aisle aisle;

    @Embedded
    private Row row;

    @Embedded
    private Shelf shelf;

    @Lob
    private byte[] picture;

    public Product(UniqueInternalCode uniqueInternalCode, ShortDescription shortDescription, ExtendedDescription extendedDescription, TechnicalDescription technicalDescription, Brand brand, Reference reference, ProductionCode productionCode, Weight weight, Barcode barcode, ProductPriceDetail priceDetail, CategoryCode categoryCode, Dimension dimension, WarehouseID warehouseID, Aisle aisle, Row row, Shelf shelf, byte[] picture) {
    }

    public Product(UniqueInternalCode uniqueInternalCode, ShortDescription shortDescription, ExtendedDescription extendedDescription) {
        this.uniqueInternalCode = uniqueInternalCode;
        this.shortDescription = shortDescription;
        this.extendedDescription = extendedDescription;
    }


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Warehouse warehouse;

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    public void setRow(Row row) {
        this.row = row;
    }

    public void setAisle(Aisle aisle) {
        this.aisle = aisle;
    }

    public Product() {

    }

    public byte[] picture() {
        return Arrays.copyOf(picture, picture.length);
    }

    public boolean hasPicture() {
        return picture != null && picture.length != 0;
    }


    public void changePicture(final byte[] picture) {
        this.picture = Arrays.copyOf(picture, picture.length);
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

    public Category getCategory() {
        return categoryCode;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public ProductPriceDetail getPriceDetail() {
        return priceDetail;
    }

    public WarehouseID getWarehouseID() {
        return warehouseID;
    }

    public Aisle getAisle() {
        return aisle;
    }

    public Row getRow() {
        return row;
    }

    public Shelf getShelf() {
        return shelf;
    }


    @Override
    public String toString() {
        return "Product:" +
                "uniqueInternalCode=" + uniqueInternalCode +
                ", shortDescription=" + shortDescription +
                ", extendedDescription=" + extendedDescription +
                ", technicalDescription=" + technicalDescription +
                ", brand=" + brand +
                ", reference=" + reference +
                ", productionCode=" + productionCode +
                ", weight=" + weight +
                ", barcode=" + barcode +
                ", priceDetail=" + priceDetail +
                ", category=" + categoryCode +
                ", dimension=" + dimension +
                ", warehouseID=" + warehouseID ;
    }
}