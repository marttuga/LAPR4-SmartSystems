package eapli.base.app.backoffice.console.presentation.UI;


import eapli.base.utilitarianClasses.Utils;
import eapli.base.ordersmanagement.category.application.DefineCategoryController;
import eapli.base.ordersmanagement.category.domain.Category;


import eapli.base.ordersmanagement.product.application.ViewCatalogController;
import eapli.base.ordersmanagement.product.domain.Brand;
import eapli.base.ordersmanagement.product.domain.Product;

import eapli.base.ordersmanagement.product.domain.UniqueInternalCode;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;


public class ViewCatalogUI extends AbstractUI {
    private static final ViewCatalogController catalogueController = new ViewCatalogController();
    private static final DefineCategoryController categoryController = new DefineCategoryController();

    public boolean doShow() {
        int optionFilter = 0;
        int optionOrdering = 0;

        List<Product> productList = catalogueController.findAllProducts();
        catalogueController.printProductsList(productList);
        List<Product> productListt= new ArrayList<>();

/////////FILTERING MENU
        optionFilter = catalogueController.showOptionsFilter();
        //ORDENAR A LISTA DE PRODUTOS
        switch (optionFilter) {
            case 0:
                System.out.println("Exiting ...");
                break;

            case 1:
                String categoryCode = Utils.readLine("Category code: ");
                productListt=catalogueController.printCategoriesList(categoryCode);
                break;

            case 2:
                String brandName = Utils.readLine("Brand: ");
                productListt=catalogueController.printBrandList(brandName);
                break;

            case 3:
                String description = Utils.readLine("Description: ");
                productListt=catalogueController.printDescriptionList(description);
                break;
    /*        case 4:
                Brand and Category();
                break;
            case 5:
                Brand and Short Description();
                break;
            case 6:
                Short Description and Category();
                break;
            case 7:
                Brand, Short Description and Category();
                break;*/

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


        //////ESCOLHER PRODUTO e imprimir
        String productCode = Utils.readLine("Insert Product Internal Code: ");

        Product product = catalogueController.findByProductCode(productCode);
        System.out.println(product);

        return false;
    }

    @Override
    public String headline() {
        return "View the catalog";
    }


}
