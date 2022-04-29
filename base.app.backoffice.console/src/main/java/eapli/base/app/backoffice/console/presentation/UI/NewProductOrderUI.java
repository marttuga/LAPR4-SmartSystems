package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.Utils.Utils;
import eapli.base.ordersmanagement.category.application.DefineCategoryController;
import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.category.domain.CategoryCode;
import eapli.base.ordersmanagement.customer.application.RegisterCustomerController;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.customer.domain.CustomerId;
import eapli.base.ordersmanagement.order.application.NewProductOrderController;

import eapli.base.ordersmanagement.product.application.ViewCatalogController;
import eapli.base.ordersmanagement.product.domain.*;
import eapli.framework.presentation.console.AbstractUI;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.List;


public class NewProductOrderUI extends AbstractUI {
    private static final ViewCatalogController catalogueController = new ViewCatalogController();
    private static final NewProductOrderController productOrderController = new NewProductOrderController();
    private static final RegisterCustomerController registerCustomerController = new RegisterCustomerController();
    private static final DefineCategoryController categoryController = new DefineCategoryController();

    public boolean doShow() {
        boolean continueRun;
        do {
            continueRun = false;
            int costumerID = Utils.readIntegerFromConsole("Please enter the costumerID: " + "\n(must have 7 numbers)");
            CustomerId code = new CustomerId(costumerID);
            Customer customer = registerCustomerController.findByCustomerId(code);
            System.out.println(customer);


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
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                System.out.println("Product does not exist");
            }


            Calendar orderDate = Calendar.getInstance();







       /* final Dish theDish = selectDish();

        controller.registerMeal(MealType.LUNCH, currentDay, theDish);
    } catch (@SuppressWarnings("unused") final IntegrityViolationException ex) {
        System.out.println("There is already a meal for that day/type in that menu");
    }
            return false;
}

    private Dish selectDish() {
        System.out.println("List of Dishes - Select a Dish");
        final Iterable<Dish> listDish = listDishService.allDishes();
        final SelectWidget<Dish> selectorDish = new SelectWidget<>("Select a dish", listDish,
                new DishPrinter());
        selectorDish.show();
        return selectorDish.selectedElement();
    }

    @Override
    public String headline() {
        return "Register Meal On a Menu";
    }

*/
            return false;


        } while (continueRun);
    }

    @Override
    public String headline() {
        return "Make a Product Order";
    }
}
