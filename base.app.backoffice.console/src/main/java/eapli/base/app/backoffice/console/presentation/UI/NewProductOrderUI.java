/*
package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.Utils.Utils;
import eapli.base.ordersmanagement.category.application.DefineCategoryController;
import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.category.domain.CategoryCode;
import eapli.base.ordersmanagement.customer.application.RegisterCustomerController;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.customer.domain.CustomerId;
import eapli.base.ordersmanagement.order.application.NewProductOrderController;

import eapli.base.ordersmanagement.order.domain.OrderID;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.order.domain.Status;
import eapli.base.ordersmanagement.product.application.ViewCatalogController;
import eapli.base.ordersmanagement.product.domain.*;
import eapli.framework.presentation.console.AbstractUI;

import java.util.*;
*/

/*
public class NewProductOrderUI extends AbstractUI {
    private static final ViewCatalogController catalogueController = new ViewCatalogController();
    private static final NewProductOrderController productOrderController = new NewProductOrderController();
    private static final RegisterCustomerController registerCustomerController = new RegisterCustomerController();
    private static final DefineCategoryController categoryController = new DefineCategoryController();

    public boolean doShow() {

        String orderActorID = Utils.readLineFromConsole("Please enter the Sales Clerck ID: ");
        boolean continueRun;
        do {
            continueRun = false;
            Customer customer;
            do {
                int costumerID = Utils.readIntegerFromConsole("Please enter the costumerID: " + "\n(must have 7 numbers)");
                CustomerId code = new CustomerId(costumerID);
                customer = registerCustomerController.findByCustomerId(code);
                System.out.println(customer);
            } while (registerCustomerController.findAllCustomers().contains(customer));

            int optionFilter = 0;
            int optionOrdering = 0;

            List<Product> productList = (List<Product>) catalogueController.findAllProducts();
            catalogueController.printProductsList(productList);

            do {
/////////FILTERING MENU
                optionFilter = catalogueController.showOptionsFilter();
                switch (optionFilter) {
                    case 0:
                        System.out.println("Exiting ...");
                        break;

                    case 1:

                        List<Category> categoryList = categoryController.findAllCategories();
                        catalogueController.printCategoriesList(categoryList);
                        String categoryCode = Utils.readLine("Category code: ");
                        CategoryCode catCode = new CategoryCode(categoryCode);
                        Category category = categoryController.findByCategoryCode(catCode);
                        System.out.println();
                        System.out.println("               Catalog :             ");
                        System.out.println();
                        List<Product> productCategoryList = catalogueController.getProductByCategory(category);
                        catalogueController.printProductsList(productCategoryList);
                        break;

                    case 2:

                        List<Brand> brandList = catalogueController.findAllBrands();
                        catalogueController.printBrandsList(brandList);
                        String brandName = Utils.readLine("Brand: ");
                        Brand brand = catalogueController.findByBrandName(brandName);
                        System.out.println();
                        System.out.println("               Catalog :             ");
                        System.out.println();
                        List<Product> productBrandList = catalogueController.getProductByBrand(brand);
                        catalogueController.printProductsList(productBrandList);
                        break;

                    case 3:
                        String description = Utils.readLine("Description: ");
                        ShortDescription shortDescription = catalogueController.findByShortDescription(description);
                        System.out.println();
                        System.out.println("               Catalog :             ");
                        System.out.println();
                        List<Product> productDescriptionList = (List<Product>) catalogueController.getProductByDescription(shortDescription);
                        catalogueController.printProductsList(productDescriptionList);
                        break;
    *//*        case 4:
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
                break;*//*

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
                        System.out.println();
                        System.out.println("               Catalog :             ");
                        System.out.println();
                        List<Product> producttList = (List<Product>) catalogueController.findAllProducts();
                        catalogueController.sortByDescription(producttList);
                        catalogueController.printProductsList(producttList);
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("               Catalog :             ");
                        System.out.println();
                        List<Product> catalogueList = (List<Product>) catalogueController.findAllProducts();
                        catalogueController.sortByPrice(catalogueList);
                        catalogueController.printProductsList(catalogueList);
                        break;
                    default:
                        System.out.println("Option does not exist!");
                        break;
                }
            } while (optionOrdering != 0);


            //////ESCOLHER PRODUTO e imprimir
            String productCode = Utils.readLine("Insert Product Internal Code: ");
            try {
                UniqueInternalCode pcode = new UniqueInternalCode(productCode);
                Product product = catalogueController.findByProductCode(pcode);
                System.out.println(product);

                int quantity = Utils.readIntegerFromConsole("Insert the quantity of the product: ");

            } catch (IllegalAccessException e) {
                e.printStackTrace();
                System.out.println("Product does not exist");
            }

        }while (continueRun);



        Calendar orderDate = Calendar.getInstance();

        Random rand = new Random();
        String id = String.valueOf(rand.nextInt(999999999));
        OrderID orderID = new OrderID(id);
        ProductOrder order = productOrderController.registerNewOrder(productOrderController.orderActor(orderActorID), orderID, customer, orderDate, , , , , Status.REGISTERED);


        return false;


    }


    @Override
    public String headline() {
        return "Make a Product Order";
    }
}*/
