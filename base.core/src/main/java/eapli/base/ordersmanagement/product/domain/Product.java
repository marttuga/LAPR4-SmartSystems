package eapli.base.ordersmanagement.product.domain;

import eapli.framework.domain.model.AggregateRoot;

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

/*    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category")
    private Category category;*/

    @Embedded
    private Dimension dimension;

    public Product() {
    }

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