package eapli.base.ordersmanagement.product.domain;

import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;
import java.io.Serializable;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "warehouse")
    private Warehouse warehouse;


    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public int compareTo(UniqueInternalCode other) {
        return AggregateRoot.super.compareTo(other);
    }

    @Override
    public UniqueInternalCode identity() {
        return null;
    }

    @Override
    public boolean hasIdentity(UniqueInternalCode id) {
        return AggregateRoot.super.hasIdentity(id);
    }
}