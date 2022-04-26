package eapli.base.ordersmanagement.product.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author João Tiago Araújo <<1200584@isep.ipp.pt>>
 */

@Embeddable
public class ProductPriceDetail implements ValueObject, Comparable<ProductPriceDetail>{

    @Column(name = "price")
    private Double price;

    protected ProductPriceDetail() {
    }

    public ProductPriceDetail(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductPriceDetail{" +
                "price=" + price +
                '}';
    }

    @Override
    public int hashCode() {
        return this.price.hashCode();
    }

    @Override
    public int compareTo(ProductPriceDetail o) {
        return 0;
    }
}
