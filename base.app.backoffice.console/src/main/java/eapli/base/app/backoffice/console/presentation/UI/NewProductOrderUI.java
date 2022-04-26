package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.ordersmanagement.order.application.NewProductOrderController;
import eapli.base.ordersmanagement.product.application.ProductSorter;
import eapli.base.ordersmanagement.product.application.ViewCatalogController;

import java.util.Scanner;

public class NewProductOrderUI {
    private static ProductSorter productSorter = new ProductSorter();
    private static ViewCatalogController catalogueController = new ViewCatalogController();
    private static NewProductOrderController productOrderController = new NewProductOrderController();


    public static void showMenu() {
        Scanner in = new Scanner(System.in);
        int option = 0;
        do {







        } while (option != 0);
    }
}
