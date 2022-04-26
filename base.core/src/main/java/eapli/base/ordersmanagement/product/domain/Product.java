package eapli.base.ordersmanagement.product.domain;

import eapli.base.ordersmanagement.category.domain.Category;
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

    private String extendedDescription;

    private String technicalDescription;

    @Embedded
    private Brand brand;

    private String reference;

    private String productionCode;

    @Embedded
    private Barcode barcode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category")
    private Category category;

    @Embedded
    private Dimension dimension;

    public Product() {
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

    public String getExtendedDescription() {
        return extendedDescription;
    }

    public String getTechnicalDescription() {
        return technicalDescription;
    }

    public String getReference() {
        return reference;
    }

    public String getProductionCode() {
        return productionCode;
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
}