package eapli.base.ordersmanagement.shoppingCart.domain;

import eapli.base.ordersmanagement.category.domain.CategoryCode;
import eapli.base.ordersmanagement.customer.domain.*;
import eapli.base.ordersmanagement.product.domain.*;
import eapli.framework.general.domain.model.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {
    UniqueInternalCode u = new UniqueInternalCode("123456");
    ShortDescription shortDescription = new ShortDescription("oi galerinha ");
    ExtendedDescription extendedDescription = new ExtendedDescription("hello galera tudo bom com vcs");
    Barcode barcode = new Barcode("1234567890123");
    CategoryCode code= new CategoryCode("11111a");
    Product product = new Product(u,shortDescription, extendedDescription , barcode,code);

    UniqueInternalCode ur = new UniqueInternalCode("12345");
    ShortDescription shortD = new ShortDescription("oi");
    ExtendedDescription extendedD = new ExtendedDescription("hello");
    Barcode barco = new Barcode("1234321234543");
    CategoryCode coe= new CategoryCode("223232");
    Product p = new Product(ur, shortD, extendedD, barco,coe);

    Set<Product> list=new HashSet<>();


    UniqueInternalCode ura = new UniqueInternalCode("123");
    ShortDescription shorti = new ShortDescription("oio");
    ExtendedDescription extended = new ExtendedDescription("hell");
    Barcode bade = new Barcode("1111122212121");
    CategoryCode c= new CategoryCode("4432");
    Product po = new Product(ura, shorti, extended, bade,c);

    Set<Product> listt=new HashSet<>();

    Money priceItem = Money.euros(5);
    Money priceI = Money.euros(2);
    ProductItemID productItemID= new ProductItemID("3");
    ProductItemID productIID= new ProductItemID("4");


    Set<ProductItem> shopCart=new HashSet<>();
    ShoppingCartID shoppingCartID= new ShoppingCartID("1");

    CustomerId customer = new CustomerId("1");
    CustomerFirstName firstName = new CustomerFirstName("maria");
    CustomerLastName lastdName = new CustomerLastName("alice");
    CustomerEmailAdress customerEmailAddress = new CustomerEmailAdress("oi@email.pt");
    CustomerPhoneNumber customerPhoneNumber = new CustomerPhoneNumber("123456789");
    CustomerVATIdentifier customerVatIdentifier = new CustomerVATIdentifier("1234567");
    Customer cost = new Customer(customer, firstName, lastdName, customerEmailAddress, customerPhoneNumber, customerVatIdentifier);

    ShoppingCartTest() throws IllegalAccessException {
    }


    @Test
    void testToString() {
        list.add(p);
        list.add(product);
        listt.add(po);
        ProductItem productItem = new ProductItem(priceItem,2,productItemID,list);
        ProductItem productI = new ProductItem(priceI,2,productIID,listt);
        shopCart.add(productI);
        shopCart.add(productItem);
        ShoppingCart shoppingCart= new ShoppingCart(shoppingCartID,cost,shopCart);
        String expected =  "ShoppingCart:" +
                "\nshoppingCartID=" + shoppingCartID  +
                "\nproductItem=" + shopCart +
                "\ncustomer=" + cost;
        Assertions.assertEquals(expected, shoppingCart.toString());
    }


    @Test
    void getShoppingCartID() {
        list.add(p);
        list.add(product);
        listt.add(po);
        ProductItem productItem = new ProductItem(priceItem,2,productItemID,list);
        ProductItem productI = new ProductItem(priceI,2,productIID,listt);
        shopCart.add(productI);
        shopCart.add(productItem);
        ShoppingCart shoppingCart= new ShoppingCart(shoppingCartID,cost,shopCart);
        Assertions.assertEquals("1", shoppingCart.getShoppingCartID().toString());
    }

    @Test
    void getCustomer() {
        list.add(p);
        list.add(product);
        listt.add(po);
        ProductItem productItem = new ProductItem(priceItem,2,productItemID,list);
        ProductItem productI = new ProductItem(priceI,2,productIID,listt);
        shopCart.add(productI);
        shopCart.add(productItem);
        ShoppingCart shoppingCart= new ShoppingCart(shoppingCartID,cost,shopCart);
        Assertions.assertEquals("maria alice  Email: oi@email.pt", shoppingCart.getCustomer().toString());
    }

    @Test
    void getProductItem() {
        list.add(p);
        list.add(product);
        listt.add(po);
        ProductItem productItem = new ProductItem(priceItem,2,productItemID,list);
        ProductItem productI = new ProductItem(priceI,2,productIID,listt);
        shopCart.add(productI);
        shopCart.add(productItem);
        ShoppingCart shoppingCart= new ShoppingCart(shoppingCartID,cost,shopCart);
        Assertions.assertEquals(shopCart.toString(), shoppingCart.getProductItem().toString());
    }
}