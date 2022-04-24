package eapli.base.ordersmanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

/**
 *
 * @author João Tiago Araújo <<1200584@isep.ipp.pt>>
 */

@Embeddable
public class Dimension implements ValueObject {

    private Long width;

    private Long height;

    private Long lenght;

    public Dimension(Long width, Long height, Long lenght) {
        this.width = width;
        this.height = height;
        this.lenght = lenght;
    }

    public Dimension() {

    }
}
