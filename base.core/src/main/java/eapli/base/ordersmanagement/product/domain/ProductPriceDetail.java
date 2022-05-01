package eapli.base.ordersmanagement.product.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Money;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Currency;
import java.util.Objects;

/**
 *
 * @author João Tiago Araújo <<1200584@isep.ipp.pt>>
 */

@Embeddable
public class ProductPriceDetail implements ValueObject, Comparable<ProductPriceDetail>{


    @Column(insertable = false,updatable = false)
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "priceAmount")),
            @AttributeOverride(name = "currency", column = @Column(name = "priceCurrency"))})
    private Money price;

    protected ProductPriceDetail() {
    }

    public ProductPriceDetail(Money price) {
        this.price = price;
    }

    public ProductPriceDetail(String price) {

    }

    @Override
    public String toString() {
        return String.valueOf(Money.euros(price.amountAsDouble()));
    }

    public static ProductPriceDetail valueOf(final String price) throws IllegalAccessException {
        return new ProductPriceDetail(price);
    }

    public  Money getPrice() {
        return price;
    }



    @Override
    public int hashCode() {
        return Objects.hash(price);
    }

    @Override
    public int compareTo(ProductPriceDetail o) {
        return 0;
    }


}
