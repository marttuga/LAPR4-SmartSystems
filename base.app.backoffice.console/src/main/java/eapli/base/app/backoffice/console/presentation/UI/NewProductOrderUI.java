package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.ordersmanagement.category.application.DefineCategoryController;
import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.customer.application.RegisterCustomerController;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.customer.domain.CustomerId;
import eapli.base.ordersmanagement.order.application.NewProductOrderController;

import eapli.base.ordersmanagement.order.domain.*;
import eapli.base.ordersmanagement.product.application.ViewCatalogController;
import eapli.base.ordersmanagement.product.domain.*;
import eapli.base.ordersmanagement.shoppingCart.domain.ProductItem;
import eapli.base.utilitarianClasses.Utils;
import eapli.framework.presentation.console.AbstractUI;

import java.util.*;


public class NewProductOrderUI extends AbstractUI {
    private static final ViewCatalogController catalogueController = new ViewCatalogController();
    private static final NewProductOrderController productOrderController = new NewProductOrderController();
    private static final RegisterCustomerController registerCustomerController = new RegisterCustomerController();
    private static final DefineCategoryController categoryController = new DefineCategoryController();

    public boolean doShow() {

        String orderActorID = Utils.readLineFromConsole("Please enter the Sales Clerck ID: ");

        Customer customer;
        do {
            int costumerID = Utils.readIntegerFromConsole("Please enter the costumerID: " + "\n(must have 7 numbers)");
            CustomerId code = new CustomerId(costumerID);
            customer = registerCustomerController.findByCustomerId(code);
            System.out.println(customer);
        } while (registerCustomerController.findAllCustomers().contains(customer));

        int optionFilter = 0;
        int optionOrdering = 0;

        List<Product> productList =  catalogueController.findAllProducts();
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

        LineOrder lineOrder = null;
        do {

            String productCode = Utils.readLine("Insert Product Internal Code: ");
            try {
                UniqueInternalCode pcode = new UniqueInternalCode(productCode);
                Product product = catalogueController.findByProductCode(pcode);
                System.out.println(product);

                int quantity = Utils.readIntegerFromConsole("Insert the quantity of the product: ");
                Set<Product> p = new HashSet<>();
                for (int i = 0; i < quantity; i++) {
                    p.add(product);
                }
                ProductItem productItem = productOrderController.productItem(p, quantity);

                Set<ProductItem> pi = new HashSet<>();
                pi.add(productItem);
                lineOrder = productOrderController.lineOrder(pi);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
                System.out.println("Product does not exist");
            }
        } while (Utils.confirm("Want to add more products? (y/n)"));


        int pm = Utils.readIntegerFromConsole("Choose the payment method: \n");
        PaymentMethod paymentMethod = productOrderController.paymentMethod(pm);

        int sm = Utils.readIntegerFromConsole("Choose the shipping method: \n");
        ShippingMethod shippingMethod = productOrderController.shippingMethod(sm);

        int sr = Utils.readIntegerFromConsole("Choose the region: \n");
        SalesRegion salesRegion = productOrderController.salesRegion(sr);

        PriceOrder priceOrder = productOrderController.priceOfOrder(lineOrder, salesRegion, shippingMethod);

        Calendar orderDate = Calendar.getInstance();

        Random rand = new Random();
        String id = String.valueOf(rand.nextInt(999999999));
        OrderID orderID = new OrderID(id);

        ProductOrder order = productOrderController.registerNewOrder(productOrderController.orderActor(orderActorID), orderID, customer, orderDate, lineOrder, priceOrder, paymentMethod, shippingMethod, Status.REGISTERED);
        System.out.println(order);

        return false;
    }

    @Override
    public String headline() {
        return "Make a Product Order";
    }
}
