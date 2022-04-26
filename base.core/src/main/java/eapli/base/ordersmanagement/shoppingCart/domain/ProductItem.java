package eapli.base.ordersmanagement.shoppingCart.domain;

import javax.persistence.*;


@Entity
public class ProductItem{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ProductItemId productItemId;


    public ProductItem(ProductItemId productItemId) {
        this.productItemId = productItemId;
    }

    protected ProductItem() {

    }
}
