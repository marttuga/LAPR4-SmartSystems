package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.ordersmanagement.product.application.ProductSorter;
import eapli.base.ordersmanagement.product.application.ViewCatalogController;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;

import java.util.List;
import java.util.Scanner;

public class ViewCatalogUI {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private static final ProductSorter productSorter = new ProductSorter();
    private static final ViewCatalogController catalogueController = new ViewCatalogController();


    public static void showMenu() {
        Scanner in = new Scanner(System.in);
        int option = 0;
        do {

            List<Product> productList = (List<Product>) catalogueController.getAllProducts();
            printProductsList(productList);

            option = showOptions();
            //ORDENAR A LISTA DE PRODUTOS
            switch (option) {
                case 0:
                    System.out.println("Exiting ...");
                    break;
                case 1:
                    sortByDescription();
                    break;
                case 2:
                    sortByPrice();
                    break;

                default:
                    System.out.println("Option does not exist!");
                    break;
            }
        } while (option != 0);
    }

    private static int showOptions() {
        int option = -1;
        System.out.println("===================================================");
        System.out.println("               Search Catalogue Menu:          ");
        System.out.println("=================================================\n");
        System.out.println("1-Sort Catalogues by Description");
        System.out.println("2-Sort Catalogues by Price");
        System.out.println("===================================================");
        System.out.println("0. Back\n\n");
        option = Console.readInteger("Please select an option");
        return option;
    }

    private static List<Product> sortByDescription() {
        System.out.println("===================================================");
        System.out.println("               Registered Catalog :             ");
        System.out.println("=================================================\n");
        List<Product> productList = (List<Product>) catalogueController.getAllProducts();
        productSorter.sortByDescription(productList);
        printProductsList(productList);

        return productList;
    }

    private static List<Product> sortByPrice() {
        System.out.println("===================================================");
        System.out.println("               Registered Catalog :             ");
        System.out.println("=================================================\n");
        List<Product> catalogueList = (List<Product>) catalogueController.getAllProducts();
        productSorter.sortByPrice(catalogueList);
        printProductsList(catalogueList);

        return catalogueList;
    }

    public static void printProductsList(List<Product> productList) {
        for (Product c : productList) {
            System.out.println("Code- " + c.getUniqueInternalCode() + "\nDescription- " + c.getShortDescription() +  "\nBrand- " + c.getBrand()
                    + "\nCategory: " + c.getCategory() + "\nPrice: " + c.getPriceDetail() + "\n");
        }
    }

}

