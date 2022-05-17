package eapli.base.ordersmanagement.shoppingCart.domain;

import eapli.base.ordersmanagement.category.domain.CategoryCode;
import eapli.base.ordersmanagement.customer.domain.*;
import eapli.base.ordersmanagement.order.domain.*;
import eapli.base.ordersmanagement.product.domain.*;
import eapli.framework.general.domain.model.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductItemTest {
    ProductItemTest() throws IllegalAccessException {}

    Set<Product> lista = new HashSet<>();
    Money priceItem = Money.euros(8);
    ProductItemID productItemID= new ProductItemID("3");
    UniqueInternalCode uniqueInternalCode= new UniqueInternalCode("1");
    UniqueInternalCode uniqueInternalC= new UniqueInternalCode("2");
    ShortDescription shortDescription= new ShortDescription("oi");
    ExtendedDescription extendedDescription= new ExtendedDescription("boas");
    Barcode barcode = new Barcode("1234567890765");
    CategoryCode categoryCod= new CategoryCode("12");
    Product order = new Product(uniqueInternalCode,shortDescription,extendedDescription,barcode,categoryCod);
    Product order2 = new Product(uniqueInternalC,shortDescription,extendedDescription,barcode,categoryCod);


    @Test
    void testToString() {
        lista.add(order);
        lista.add(order2);
        ProductItem productItem= new ProductItem(priceItem,2,productItemID,lista);
        String expected =  "\nProducts=" + lista +
                "\nquantity=" + 2 +
                "\npriceItem=" + priceItem;
        Assertions.assertEquals(expected, productItem.toString());
    }

    @Test
    void getProductItemID() {
        lista.add(order);
        lista.add(order2);
        ProductItem productItem= new ProductItem(priceItem,2,productItemID,lista);
        Assertions.assertEquals("3", productItem.getProductItemID().toString());
    }

    @Test
    void getProducts() {
        lista.add(order);
        lista.add(order2);
        ProductItem productItem= new ProductItem(priceItem,2,productItemID,lista);
        Assertions.assertEquals(lista.toString(), productItem.getProducts().toString());
    }

    @Test
    void getQuantity() {
        lista.add(order);
        lista.add(order2);
        ProductItem productItem= new ProductItem(priceItem,2,productItemID,lista);
        Assertions.assertEquals(2, productItem.getQuantity());
    }

    @Test
    void getPriceItem() {
        lista.add(order);
        lista.add(order2);
        ProductItem productItem= new ProductItem(priceItem,2,productItemID,lista);
        Assertions.assertEquals(8, productItem.getPriceItem().amountAsDouble());
    }
}