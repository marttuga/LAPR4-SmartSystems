package eapli.base.ordersmanagement.product.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.category.domain.CategoryCode;
import eapli.base.ordersmanagement.product.domain.Brand;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.base.ordersmanagement.product.repositories.ProductRepository;
import eapli.base.utilitarianClasses.Utils;
import eapli.framework.application.ApplicationService;
import eapli.framework.io.util.Console;

import java.util.List;

@ApplicationService
public class ViewCatalogService {


    ////////OPCOES DE FILTRAR
    public int showOptionsFilter() {
        int option = -1;
        System.out.println("===================================================\n");
        System.out.println("               FILTER BY:          ");
        System.out.println();
        System.out.println("1-Category");
        System.out.println("2-Brand");
        System.out.println("3-Short Description");
/*        System.out.println("4-Brand and Category");
        System.out.println("5-Brand and Short Description");
        System.out.println("6-Short Description and Category");
        System.out.println("7-Brand, Short Description and Category");*/
        System.out.println();
        System.out.println("0. Back\n\n");
        option = Console.readInteger("Please select an option");
        return option;
    }

    ////////OPCOES DE ORDENAR
    public int showOptionsOrdering() {
        int option = -1;
        System.out.println("===================================================");
        System.out.println("               SORT :          ");
        System.out.println("=================================================\n");
        System.out.println("1-Sort Catalogues by Description");
        System.out.println("2-Sort Catalogues by Price");
        System.out.println("===================================================");
        System.out.println("0. Back\n\n");
        option = Console.readInteger("Please select an option");
        return option;
    }

    //////PRINTING METHODS
    public void printProductsList(List<Product> productList) {
        for (Product c : productList) {
            System.out.println("Code- " + c.getUniqueInternalCode() + "\nDescription- " + c.getShortDescription().toString() + "\nBrand- " + c.getBrand()
                    + "\nCategory: " + c.getCategory() + "\nPrice: " + c.getPriceDetail() + "\n");
        }
    }

    public void printCategoriesList(List<Category> categoriesList) {
        for (Category c : categoriesList) {
            System.out.println("Category- Description- " + c.getDescription().toString() + "\nCode- " + c.getCategoryCode() + "\n");
        }
    }


    public void printHeader() {
        System.out.println();
        System.out.println("               Catalog :             ");
        System.out.println();
    }

}
