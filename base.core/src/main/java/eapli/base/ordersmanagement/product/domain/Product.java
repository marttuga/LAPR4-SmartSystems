package eapli.base.ordersmanagement.product.domain;

import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.domain.WarehouseID;
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

    @Embedded
    private Weight weight;

    @Embedded
    private Barcode barcode;

    @Embedded
    private ProductPriceDetail priceDetail;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category")
    private Category category;

    @Embedded
    private Dimension dimension;

    @Embedded
    private WarehouseID warehouseID;


    public Product() {
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "warehouse")
    private Warehouse warehouse;


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
        return category;
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

    @Override
    public String toString() {
        return "Product{" +
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
                ", category=" + category +
                ", dimension=" + dimension +
                ", warehouseID=" + warehouseID +
                ", warehouse=" + warehouse +
                '}';
    }
}