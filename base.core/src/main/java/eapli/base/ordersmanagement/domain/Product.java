package eapli.base.ordersmanagement.domain;

import javax.persistence.*;

/**
 *
 * @author João Tiago Araújo <<1200584@isep.ipp.pt>>
 */

@Entity
public class Product {

    @Id
    private String uniqueInternalCode;

    @Embedded
    private ShortDescription shortDescription;

    private String extendedDescription;

    private String technicalDescription;

    private String brand;

    private String reference;

    private String productionCode;

    @Embedded
    private Barcode barcode;

    /*@Embedded
    private Category category;*/

    @Embedded
    private Dimension dimension;

    public Product() {
    }
}