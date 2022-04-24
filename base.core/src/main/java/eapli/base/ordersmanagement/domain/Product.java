package eapli.base.ordersmanagement.domain;

import javax.persistence.*;

/**
 *
 * @author João Tiago Araújo <<1200584@isep.ipp.pt>>
 */

@Entity
public class Product {

    @Id
    @GeneratedValue
    private String uniqueInternalCode;

    private String shortDescription;

    private String extendedDescription;

    private String technicalDescription;

    private String brand;

    private String reference;

    private String productionCode;

    /*private Barcode barcode;

    private Category category;

    private Dimension dimension;*/

    public Product() {
    }
}