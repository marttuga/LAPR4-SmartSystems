package eapli.base.ordersmanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

/**
 *
 * @author João Tiago Araújo <<1200584@isep.ipp.pt>>
 */

@Embeddable
public class ShortDescription implements ValueObject {


    private String description;

    public ShortDescription(String description) {
        this.description = description;
    }

    public ShortDescription() {

    }
}
