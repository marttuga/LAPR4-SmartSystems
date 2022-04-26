package eapli.base.ordersmanagement.product.application;

import eapli.base.ordersmanagement.product.domain.Product;
import java.util.Comparator;
import java.util.List;

public class ProductSorter {

    public List<Product> sortByPrice(List<Product> productList) {

        productList.sort(new Comparator<Product>() {

            @Override
            public int compare(Product c1, Product c2) {
                return c1.getPriceDetail().compareTo(c2.getPriceDetail());
            }
        });
        return productList;
    }

    public List<Product> sortByDescription(List<Product> productList) {

        productList.sort(new Comparator<Product>() {
            @Override
            public int compare(Product c1, Product c2) {
                return c1.getShortDescription().compareTo(c2.getShortDescription());
            }
        });
        return productList;
    }


}
