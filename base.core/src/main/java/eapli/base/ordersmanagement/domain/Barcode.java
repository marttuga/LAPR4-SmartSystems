package eapli.base.ordersmanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

/**
 *
 * @author João Tiago Araújo <<1200584@isep.ipp.pt>>
 */

@Embeddable
public class Barcode implements ValueObject {

    public Long barcode;

    public Barcode(Long barcode) {
        this.barcode = barcode;
    }

    public Barcode() {

    }
}
