package eapli.base.app.backoffice.console.presentation.UI;


import eapli.base.utilitarianClasses.Utils;
import eapli.base.ordersmanagement.category.application.DefineCategoryController;
import eapli.base.ordersmanagement.category.domain.Category;


import eapli.base.ordersmanagement.product.application.ViewCatalogController;
import eapli.base.ordersmanagement.product.domain.Brand;
import eapli.base.ordersmanagement.product.domain.Product;

import eapli.base.ordersmanagement.product.domain.UniqueInternalCode;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;


public class ViewCatalogUI extends AbstractUI {
    private static final ViewCatalogController catalogueController = new ViewCatalogController();
    private static final DefineCategoryController categoryController = new DefineCategoryController();

    public boolean doShow() {
        int optionFilter = 0;
        int optionOrdering = 0;

        List<Product> productList = (List<Product>) catalogueController.findAllProducts();
        catalogueController.printProductsList(productList);

        do {

/////////FILTERING MENU
            optionFilter = catalogueController.showOptionsFilter();
            //ORDENAR A LISTA DE PRODUTOS
            switch (optionFilter) {
                case 0:
                    System.out.println("Exiting ...");
                    break;

                case 1:
                    List<Category> categoryList = categoryController.findAllCategories();
                    catalogueController.printCategoriesList(categoryList);
                    String categoryCode = Utils.readLine("Category code: ");
                    catalogueController.printCategoriesList(categoryCode);
                    break;

                case 2:
                    List<Brand> brandList = catalogueController.findAllBrands();
                    catalogueController.printBrandsList(brandList);
                    String brandName = Utils.readLine("Brand: ");
                   catalogueController.printBrandList(brandName);
                    break;

                case 3:
                    String description = Utils.readLine("Description: ");
                    catalogueController.printDescriptionList(description);
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
        } while (optionFilter != 0);

        do {
            //////ORDERING MENU
            optionOrdering = catalogueController.showOptionsOrdering();
            //ORDENAR A LISTA DE PRODUTOS
            switch (optionOrdering) {
                case 0:
                    System.out.println("Exiting ...");
                    break;
                case 1:
                   catalogueController.printOrderedDescription();
                    break;
                case 2:
                    catalogueController.printOrderedPrice();
                    break;
                default:
                    System.out.println("Option does not exist!");
                    break;
            }
        } while (optionOrdering != 0);


        //////ESCOLHER PRODUTO e imprimir
        String productCode = Utils.readLine("Insert Product Internal Code: ");
        try {
            UniqueInternalCode code = new UniqueInternalCode(productCode);
            Product product = catalogueController.findByProductCode(code);
            System.out.println(product);
        } catch (IllegalAccessException e) {
            e.printStackTrace();System.out.println("Product does not exist");
        }

        return false;
    }

    @Override
    public String headline() {
        return "View the catalog";
    }


}
