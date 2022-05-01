package eapli.base.ordersmanagement.order.domain;

import eapli.base.ordersmanagement.category.domain.CategoryCode;
import eapli.base.ordersmanagement.product.domain.*;
import eapli.base.ordersmanagement.shoppingCart.domain.ProductItem;
import eapli.framework.general.domain.model.Money;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LineOrderTest {
    UniqueInternalCode u = new UniqueInternalCode("123456");
    ShortDescription shortDescription = new ShortDescription("oi galerinha ");
    ExtendedDescription extendedDescription = new ExtendedDescription("hello galera tudo bom com vcs");
    Barcode barcode = new Barcode("1234567890123");
    CategoryCode code= new CategoryCode("11111a1111s");
    Product product = new Product(u,shortDescription, extendedDescription , barcode,code);

    UniqueInternalCode ur = new UniqueInternalCode("12345");
    ShortDescription shortD = new ShortDescription("oi");
    ExtendedDescription extendedD = new ExtendedDescription("hello");
    Barcode barco = new Barcode("1234321234543");
    CategoryCode coe= new CategoryCode("2232323s3213");
    Product p = new Product(ur, shortD, extendedD, barco,coe);

    Set<Product> list;

    UniqueInternalCode ura = new UniqueInternalCode("123");
    ShortDescription shorti = new ShortDescription("oio");
    ExtendedDescription extended = new ExtendedDescription("hell");
    Barcode bade = new Barcode("1111122212121");
    CategoryCode c= new CategoryCode("4432123212we");
    Product po = new Product(ura, shorti, extended, bade,c);

    Set<Product> listt;

    Money priceItem = Money.euros(5);
    Money priceI = Money.euros(2);

    ProductItem productItem = new ProductItem(priceItem, list, 2);
    ProductItem productI = new ProductItem(priceI, listt, 1);

    Set<ProductItem> lineOrderList;

    Money priceOrderWithoutTaxes = Money.euros(7);

    LineOrderTest() throws IllegalAccessException {
    }

    @Before
    public void setUp() throws Exception {
        list.add(product);
        list.add(p);
        listt.add(po);
        lineOrderList.add(productItem);
        lineOrderList.add(productI);

    }


    @Test
    void testToString() {
        LineOrder lineOrder = new LineOrder(lineOrderList, priceOrderWithoutTaxes);
        String expected = "LineOrder:" +
                "productItems=" + lineOrderList +
                ", priceOrderWithoutTaxes=" + priceOrderWithoutTaxes;
        Assertions.assertEquals(expected, lineOrder.toString());

    }

    @Test
    void getLoid() {
        LineOrder lineOrder = new LineOrder(lineOrderList, priceOrderWithoutTaxes);
        Assertions.assertEquals(null, lineOrder.getLoid());
    }

    @Test
    void getPriceOrderWithoutTaxes() {
        Money priceOrderWithoutTaxe = Money.euros(7);
        LineOrder lineOrder = new LineOrder(lineOrderList, priceOrderWithoutTaxes);
        Assertions.assertEquals(priceOrderWithoutTaxe, lineOrder.getPriceOrderWithoutTaxes());
    }

    @Test
    void sameAs() {
    }

    @Test
    void identity() {
    }
}