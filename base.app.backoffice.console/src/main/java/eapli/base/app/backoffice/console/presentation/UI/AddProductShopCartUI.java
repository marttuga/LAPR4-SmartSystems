package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.ordersmanagement.category.application.DefineCategoryController;
import eapli.base.ordersmanagement.customer.applicaion.RegisterCustomerController;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.customer.domain.CustomerId;
import eapli.base.ordersmanagement.order.application.NewProductOrderController;
import eapli.base.ordersmanagement.order.domain.*;
import eapli.base.ordersmanagement.product.application.ViewCatalogController;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.base.ordersmanagement.shoppingCart.domain.ProductItem;
import eapli.base.ordersmanagement.shoppingCart.domain.ShoppingCart;
import eapli.base.ordersmanagement.shoppingCart.domain.ShoppingCartID;
import eapli.base.utilitarianClasses.Utils;
import eapli.framework.presentation.console.AbstractUI;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;

public class AddProductShopCartUI extends AbstractUI {
    private static final ViewCatalogController catalogueController = new ViewCatalogController();
    private static final NewProductOrderController productOrderController = new NewProductOrderController();
    private static final RegisterCustomerController registerCustomerController = new RegisterCustomerController();

    public boolean doShow() {

        Customer customer;
        Set<ProductItem> pi = new HashSet<>();
        String costumerID = Utils.readLineFromConsole("Please enter the costumerID: " + "\n(must have 7 digits)");

        customer = registerCustomerController.findByCustomerId(CustomerId.valueOf(costumerID));
        System.out.println(customer.toString());

        System.out.println();
        do {
            int optionFilter = 0;
            int optionOrdering = 0;

            List<Product> productList = catalogueController.findAllProducts();
            catalogueController.printProductsList(productList);
            List<Product> productListt = new ArrayList<>();

/////////FILTERING MENU
            optionFilter = catalogueController.showOptionsFilter();
            //ORDENAR A LISTA DE PRODUTOS
            switch (optionFilter) {
                case 0:
                    System.out.println("Exiting ...");
                    break;

                case 1:
                    String categoryCode = Utils.readLine("Category code: ");
                    productListt = catalogueController.printCategoriesList(categoryCode);
                    break;

                case 2:
                    String brandName = Utils.readLine("Brand: ");
                    productListt = catalogueController.printBrandList(brandName);
                    break;

                case 3:
                    String description = Utils.readLine("Description: ");
                    productListt = catalogueController.printDescriptionList(description);
                    break;
                default:
                    System.out.println("Option does not exist!");
                    break;
            }


            //////ORDERING MENU
            optionOrdering = catalogueController.showOptionsOrdering();
            //ORDENAR A LISTA DE PRODUTOS
            switch (optionOrdering) {
                case 0:
                    System.out.println("Exiting ...");
                    break;
                case 1:
                    catalogueController.printOrderedDescription(productListt);
                    break;
                case 2:
                    catalogueController.printOrderedPrice(productListt);
                    break;
                default:
                    System.out.println("Option does not exist!");
                    break;
            }


            String productCode = Utils.readLine("Insert Product Internal Code: ");
            Product product = catalogueController.findByProductCode(productCode);
            System.out.println(product);

            int quantity = Utils.readIntegerFromConsole("Insert the quantity of the product: ");
            Set<Product> p = new HashSet<>();
            for (int i = 0; i < quantity; i++) {
                p.add(product);
            }
            ProductItem productItem = productOrderController.productItemSet(p, quantity);


            pi.add(productItem);

        } while (Utils.confirm("Want to add more products to the ShoppingCart? (y/n)"));

        String id = RandomStringUtils.randomAlphanumeric(6);
        ShoppingCartID shoppingCartID = new ShoppingCartID(id);

        ShoppingCart shoppingCart= new ShoppingCart(shoppingCartID,customer,pi);
        System.out.println(shoppingCart);

        return true;
    }

    @Override
    public String headline() {
        return "Add product to ShoppingCart";
    }

}
