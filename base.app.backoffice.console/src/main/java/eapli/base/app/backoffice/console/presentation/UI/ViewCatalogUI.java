package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.Utils;
import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.product.application.ProductSorter;
import eapli.base.ordersmanagement.product.application.ViewCatalogController;
import eapli.base.ordersmanagement.product.domain.Brand;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.base.ordersmanagement.product.domain.ShortDescription;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.apache.commons.lang3.StringUtils;

import java.util.List;


public class ViewCatalogUI extends AbstractUI {

    private static final ProductSorter productSorter = new ProductSorter();
    private static final ViewCatalogController catalogueController = new ViewCatalogController();
    // private static final ViewCategoryController categoryController = new ViewCategoryController();

    public boolean doShow() {
        int optionFilter = 0;
        int optionOrdering = 0;

        do {

            List<Product> productList = (List<Product>) catalogueController.findAllProducts();
            printProductsList(productList);

/////////FILTERING MENU
            optionFilter = showOptionsFilter();
            //ORDENAR A LISTA DE PRODUTOS
            switch (optionFilter) {
                case 0:
                    System.out.println("Exiting ...");
                    break;

                case 1:

           /*         List<Category> categoryList = (List<Category>) categoryController.findAllCategories();
                    printCategoriesList(categoryList);
                    String categoryCode = Utils.readLine("Category code: ");
                    Category category = categoryController.getCategoryByCode();
                    System.out.println();
                    System.out.println("               Catalog :             ");
                    System.out.println();
                    List<Product> productCategoryList = catalogueController.getProductByCategory(category);
                    printProductsList(productCategoryList);
                    break;
*/
                case 2:

                    List<Brand> brandList = (List<Brand>) catalogueController.findAllBrands();
                    printBrandsList(brandList);
                    String brandName = Utils.readLine("Brand: ");
                    Brand brand = catalogueController.findByBrandName(brandName);
                    System.out.println();
                    System.out.println("               Catalog :             ");
                    System.out.println();
                    List<Product> productBrandList = catalogueController.getProductByBrand(brand);
                    printProductsList(productBrandList);
                    break;

                case 3:
                    String description = Utils.readLine("Description: ");
                    ShortDescription shortDescription = catalogueController.findByShortDescription(description);
                    System.out.println();
                    System.out.println("               Catalog :             ");
                    System.out.println();
                    List<Product> productDescriptionList = (List<Product>) catalogueController.getProductByDescription(shortDescription);
                    printProductsList(productDescriptionList);
                    break;
    /*        case 4:
                sortByPrice();
                break;
            case 5:
                sortByDescription();
                break;
            case 6:
                sortByPrice();
                break;
            case 7:
                sortByPrice();
                break;*/

                default:
                    System.out.println("Option does not exist!");
                    break;
            }
        } while (optionFilter != 0);

        do {
            //////ORDERING MENU
            optionOrdering = showOptionsOrdering();
            //ORDENAR A LISTA DE PRODUTOS
            switch (optionOrdering) {
                case 0:
                    System.out.println("Exiting ...");
                    break;
                case 1:
                    System.out.println();
                    System.out.println("               Catalog :             ");
                    System.out.println();
                    List<Product> productList = (List<Product>) catalogueController.findAllProducts();
                    productSorter.sortByDescription(productList);
                    printProductsList(productList);
                    break;
                case 2:
                    System.out.println();
                    System.out.println("               Catalog :             ");
                    System.out.println();
                    List<Product> catalogueList = (List<Product>) catalogueController.findAllProducts();
                    productSorter.sortByPrice(catalogueList);
                    printProductsList(catalogueList);
                    break;
                default:
                    System.out.println("Option does not exist!");
                    break;
            }
        } while (optionOrdering != 0);


        //////ESCOLHER PRODUTO
        String productCode = Utils.readLine("Insert Product Internal Code: ");
        if (productCode.length() > 23 || StringUtils.isBlank(productCode)) {
            System.out.println("\nThe data inserted was not valid! Please try again");
        } else {
            Product product = catalogueController.findByProductCode(productCode);
            System.out.println(product);
        }
        System.out.println("Product does not exist");


        return false;
    }

    @Override
    public String headline() {
        return "View the catalog";
    }


    ////////OPCOES DE FILTRAR
    private static int showOptionsFilter() {
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
    private static int showOptionsOrdering() {
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


    //////PRINTING METHODS
    public static void printProductsList(List<Product> productList) {
        for (Product c : productList) {
            System.out.println("Code- " + c.getUniqueInternalCode() + "\nDescription- " + c.getShortDescription() + "\nBrand- " + c.getBrand()
                    + "\nCategory: " + c.getCategory() + "\nPrice: " + c.getPriceDetail() + "\n");
        }
    }

/*
    public static void printCategoriesList(List<Category> categoriesList) {
        for (Category c : categoriesList) {
            System.out.println("Category- " + c.getUniqueInternalCode() + "\nDescription- " + c.getShortDescription() + "\nCode- " + c.getBrand() + "\n");
        }
    }
*/

    public static void printBrandsList(List<Brand> brandsList) {
        for (Brand c : brandsList) {
            System.out.println("Brand- " + c.getBrandName() + "\n");
        }
    }

}
