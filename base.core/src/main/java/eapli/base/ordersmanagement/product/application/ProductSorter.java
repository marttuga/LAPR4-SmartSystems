package eapli.base.ordersmanagement.product.application;

import eapli.base.ordersmanagement.product.domain.Product;

import java.util.Comparator;
import java.util.List;

public class ProductSorter {

    public List<Product> sortByPrice(List<Product> productList) {

        productList.sort(Comparator.comparing((Product p) ->p.getPriceDetail().getPrice()));
        return productList;
    }

    public List<Product> sortByDescription(List<Product> productList) {

        productList.sort(Comparator.comparing((Product p) ->p.getShortDescription().toString()));
        return productList;
    }
}
